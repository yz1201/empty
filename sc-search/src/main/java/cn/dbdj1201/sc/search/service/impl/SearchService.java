package cn.dbdj1201.sc.search.service.impl;

import cn.dbdj1201.sc.item.pojo.*;
import cn.dbdj1201.sc.search.client.BrandClient;
import cn.dbdj1201.sc.search.client.CategoryClient;
import cn.dbdj1201.sc.search.client.GoodsClient;
import cn.dbdj1201.sc.search.client.SpecificationClient;
import cn.dbdj1201.sc.search.pojo.Goods;
import cn.dbdj1201.sc.search.service.ISearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Map<String,Object> specs = new HashMap<>();
        params.forEach(param->{
            if(param.getGeneric()) {
                String value = genericSpecMap.get(param.getId().toString()).toString();
                if(param.getNumeric()){
                    value = chooseSegment(value, param);
                }
                specs.put(param.getName(),value);
            }else {
                List<Object> value = specialSpecMap.get(param.getId().toString());
                specs.put(param.getName(),value);
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

    private String chooseSegment(String value, SpecParam p) {
        double val = NumberUtils.toDouble(value);
        String result = "其它";
        // 保存数值段
        for (String segment : p.getSegments().split(",")) {
            String[] segs = segment.split("-");
            // 获取数值范围
            double begin = NumberUtils.toDouble(segs[0]);
            double end = Double.MAX_VALUE;
            if(segs.length == 2){
                end = NumberUtils.toDouble(segs[1]);
            }
            // 判断是否在范围内
            if(val >= begin && val < end){
                if(segs.length == 1){
                    result = segs[0] + p.getUnit() + "以上";
                }else if(begin == 0){
                    result = segs[1] + p.getUnit() + "以下";
                }else{
                    result = segment + p.getUnit();
                }
                break;
            }
        }
        return result;
    }

}
