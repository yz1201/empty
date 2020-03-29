package cn.dbdj1201.sc.search.service.impl;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.item.pojo.*;
import cn.dbdj1201.sc.search.client.BrandClient;
import cn.dbdj1201.sc.search.client.CategoryClient;
import cn.dbdj1201.sc.search.client.GoodsClient;
import cn.dbdj1201.sc.search.client.SpecificationClient;
import cn.dbdj1201.sc.search.pojo.Goods;
import cn.dbdj1201.sc.search.pojo.SearchRequest;
import cn.dbdj1201.sc.search.pojo.SearchResult;
import cn.dbdj1201.sc.search.repository.GoodsRepository;
import cn.dbdj1201.sc.search.service.ISearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author tyz1201
 * @datetime 2020-03-25 22:34
 **/
@Service
public class SearchService implements ISearchService {

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private SpecificationClient specificationClient;

    @Autowired
    private GoodsRepository goodsRepository;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public Goods buildGoods(Spu spu) throws JsonProcessingException {
        Goods goods = new Goods();
        //根据分类id查询分类名称
        List<String> names = this.categoryClient.queryNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        //根据品牌id查询品牌
        Brand brand = this.brandClient.queryBrandById(spu.getBrandId());
        //根据spu id 查询包含的sku
        List<Sku> skus = this.goodsClient.querySkusBySpuId(spu.getId());
        //封装sku的必要字段信息
        List<Map<String, Object>> skuMapList = new ArrayList<>();

        this.goodsClient.querySpuByPage(null, true, 1, 10).getItems().forEach(System.out::println);

        //根据sku获得其价格
        List<Long> prices = new ArrayList<>();
        skus.forEach(sku -> {
            prices.add(sku.getPrice());
            //封装sku为必要的字段
            Map<String, Object> skuMap = new HashMap<>();
            skuMap.put("id", sku.getId());
            skuMap.put("title", sku.getTitle());
            skuMap.put("image", StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            skuMap.put("price", sku.getPrice());
            skuMapList.add(skuMap);
        });

        SpuDetail spuDetail = this.goodsClient.querySpuDetailBySpuId(spu.getId());
        //根据cid3 查询出所有的搜索规格参数
        List<SpecParam> params = this.specificationClient.listParams(null, spu.getCid3(), null, true);
        //通用规格参数集合反序列化
        Map<String, Object> genericSpecMap = MAPPER.readValue(spuDetail.getGenericSpec(), new TypeReference<>() {
        });
        //特殊规格参数集合反序列化
        Map<String, List<Object>> specialSpecMap = MAPPER.readValue(spuDetail.getSpecialSpec(), new TypeReference<>() {
        });
        Map<String, Object> specs = new HashMap<>();
        params.forEach(param -> {
            if (param.getGeneric()) {
                String value = genericSpecMap.get(param.getId().toString()).toString();
                if (param.getNumeric()) {
                    value = chooseSegment(value, param);
                }
                specs.put(param.getName(), value);
            } else {
                List<Object> value = specialSpecMap.get(param.getId().toString());
                specs.put(param.getName(), value);
            }
        });

        goods.setId(spu.getId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setBrandId(spu.getBrandId());
        goods.setCreateTime(spu.getCreateTime());
        goods.setSubTitle(spu.getSubTitle());
        //拼接搜索字段，spu的标题，三级分类名称，品牌名称
        goods.setAll(spu.getTitle() + " " + StringUtils.join(names, " ") + " " + brand.getName());
        //设置sku价格集合
        goods.setPrice(prices);
        //
        goods.setSkus(MAPPER.writeValueAsString(skuMapList));
        //
        goods.setSpecs(specs);
        return goods;
    }

    @Override
    public SearchResult search(SearchRequest searchRequest) {
        if (StringUtils.isBlank(searchRequest.getKey()))
            return null;
        //分页参数
        int page = searchRequest.getPage();
        int size = searchRequest.getSize();
        //自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = buildBooleanQueryBuilder(searchRequest);
        queryBuilder.withQuery(boolQueryBuilder);
        //分词查询
        MatchQueryBuilder basicQuery = QueryBuilders.matchQuery("all", searchRequest.getKey()).operator(Operator.AND);
        queryBuilder.withQuery(basicQuery);
        //分页查询，页码从0开始
        queryBuilder.withPageable(PageRequest.of(page - 1, size));
        //添加结果集过滤
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"id", "skus", "subTitle"}, null));
        String sortBy = searchRequest.getSortBy();
        Boolean desc = searchRequest.getDesc();
        if (StringUtils.isNotBlank(sortBy)) {
            queryBuilder.withSort(SortBuilders.fieldSort(sortBy).order(desc ? SortOrder.DESC : SortOrder.ASC));
        }
        //聚合，桶
        String categoryAggName = "categories";
        String brandAggName = "brands";
        //增加聚合
        queryBuilder.addAggregation(AggregationBuilders.terms(categoryAggName).field("cid3"));
        queryBuilder.addAggregation(AggregationBuilders.terms(brandAggName).field("brandId"));
        AggregatedPage<Goods> goodsPage = (AggregatedPage<Goods>) this.goodsRepository.search(queryBuilder.build());
        //解析聚合结果集
        List<Map<String, Object>> categories = getCategoryAggResult(goodsPage.getAggregation(categoryAggName));
        List<Brand> brands = getBrandAggResult(goodsPage.getAggregation(brandAggName));

        List<Map<String, Object>> specs = null;
        if (categories.size() == 1) {
            specs = getParamAggResult((Long) categories.get(0).get("id"), boolQueryBuilder);
        }

        return new SearchResult(goodsPage.getContent(), goodsPage.getTotalElements(), goodsPage.getTotalPages(), categories, brands, specs);
    }

    /**
     * 解析品牌聚合结果集
     *
     * @param aggregation
     * @return
     */
    private List<Brand> getBrandAggResult(Aggregation aggregation) {
        // 处理聚合结果集
        LongTerms terms = (LongTerms) aggregation;
        // 获取所有的品牌id桶
        List<LongTerms.Bucket> buckets = terms.getBuckets();
        // 定义一个品牌集合，搜集所有的品牌对象
        List<Brand> brands = new ArrayList<>();
        // 解析所有的id桶，查询品牌
        buckets.forEach(bucket -> {
            Brand brand = this.brandClient.queryBrandById(bucket.getKeyAsNumber().longValue());
            brands.add(brand);
        });
        return brands;
        // 解析聚合结果集中的桶，把桶的集合转化成id的集合
        // List<Long> brandIds = terms.getBuckets().stream().map(bucket -> bucket.getKeyAsNumber().longValue()).collect(Collectors.toList());
        // 根据ids查询品牌
        //return brandIds.stream().map(id -> this.brandClient.queryBrandById(id)).collect(Collectors.toList());
        // return terms.getBuckets().stream().map(bucket -> this.brandClient.queryBrandById(bucket.getKeyAsNumber().longValue())).collect(Collectors.toList());
    }

    /**
     * 解析分类
     *
     * @param aggregation
     * @return
     */
    private List<Map<String, Object>> getCategoryAggResult(Aggregation aggregation) {
        // 处理聚合结果集
        LongTerms terms = (LongTerms) aggregation;
        // 获取所有的分类id桶
        List<LongTerms.Bucket> buckets = terms.getBuckets();
        // 定义一个品牌集合，搜集所有的品牌对象
        List<Map<String, Object>> categories = new ArrayList<>();
        List<Long> cids = new ArrayList<>();
        // 解析所有的id桶，查询品牌
        buckets.forEach(bucket -> cids.add(bucket.getKeyAsNumber().longValue()));
        List<String> names = this.categoryClient.queryNamesByIds(cids);
        for (int i = 0; i < cids.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", cids.get(i));
            map.put("name", names.get(i));
            categories.add(map);
        }
        return categories;
    }

    /**
     * 聚合出规格参数过滤条件
     *
     * @param id
     * @param basicQuery
     * @return
     */
    private List<Map<String, Object>> getParamAggResult(Long id, QueryBuilder basicQuery) {
        // 创建自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 基于基本的查询条件，聚合规格参数
        queryBuilder.withQuery(basicQuery);
        // 查询要聚合的规格参数
        List<SpecParam> params = this.specificationClient.listParams(null, id, null, true);
        // 添加聚合
        params.forEach(param -> queryBuilder.addAggregation(AggregationBuilders.terms(param.getName()).field("specs." + param.getName() + ".keyword")));
        // 只需要聚合结果集，不需要查询结果集
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{}, null));

        // 执行聚合查询
        AggregatedPage<Goods> goodsPage = (AggregatedPage<Goods>) this.goodsRepository.search(queryBuilder.build());

        // 定义一个集合，收集聚合结果集
        List<Map<String, Object>> paramMapList = new ArrayList<>();
        // 解析聚合查询的结果集
        Map<String, Aggregation> aggregationMap = goodsPage.getAggregations().asMap();
        for (Map.Entry<String, Aggregation> entry : aggregationMap.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            // 放入规格参数名
            map.put("k", entry.getKey());
            // 收集规格参数值
            List<Object> options = new ArrayList<>();
            // 解析每个聚合
            StringTerms terms = (StringTerms) entry.getValue();
            // 遍历每个聚合中桶，把桶中key放入收集规格参数的集合中
            terms.getBuckets().forEach(bucket -> options.add(bucket.getKeyAsString()));
            map.put("options", options);
            paramMapList.add(map);
        }

        return paramMapList;
    }

    /**
     * 构建bool查询构建器
     *
     * @param request
     * @return
     */
    private BoolQueryBuilder buildBooleanQueryBuilder(SearchRequest request) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 添加基本查询条件
        boolQueryBuilder.must(QueryBuilders.matchQuery("all", request.getKey()).operator(Operator.AND));

        // 添加过滤条件
        if (CollectionUtils.isEmpty(request.getFilter())) {
            return boolQueryBuilder;
        }
        for (Map.Entry<String, Object> entry : request.getFilter().entrySet()) {
            String key = entry.getKey();
            // 如果过滤条件是“品牌”, 过滤的字段名：brandId
            if (StringUtils.equals("品牌", key)) {
                key = "brandId";
            } else if (StringUtils.equals("分类", key)) {
                // 如果是“分类”，过滤字段名：cid3
                key = "cid3";
            } else {
                // 如果是规格参数名，过滤字段名：specs.key.keyword
                key = "specs." + key + ".keyword";
            }
            boolQueryBuilder.filter(QueryBuilders.termQuery(key, entry.getValue()));
        }
        return boolQueryBuilder;
    }

    private String chooseSegment(String value, SpecParam p) {
        double val = NumberUtils.toDouble(value);
        String result = "其它";
        // 保存数值段
        for (String segment : p.getSegments().split(",")) {
            String[] segs = segment.split("-");
            // 获取数值范围
            double begin = NumberUtils.toDouble(segs[0]);
            double end = Double.MAX_VALUE;
            if (segs.length == 2) {
                end = NumberUtils.toDouble(segs[1]);
            }
            // 判断是否在范围内
            if (val >= begin && val < end) {
                if (segs.length == 1) {
                    result = segs[0] + p.getUnit() + "以上";
                } else if (begin == 0) {
                    result = segs[1] + p.getUnit() + "以下";
                } else {
                    result = segment + p.getUnit();
                }
                break;
            }
        }
        return result;
    }

}
