package cn.dbdj1201.goods.web.service.impl;

import cn.dbdj1201.goods.web.client.BrandClient;
import cn.dbdj1201.goods.web.client.CategoryClient;
import cn.dbdj1201.goods.web.client.GoodsClient;
import cn.dbdj1201.goods.web.client.SpecificationClient;
import cn.dbdj1201.goods.web.service.IGoodsService;
import cn.dbdj1201.sc.item.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author tyz1201
 * @datetime 2020-03-30 16:34
 **/
@Service
public class GoodsService implements IGoodsService {

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private SpecificationClient specificationClient;

    @Override
    public Map<String, Object> loadData(Long spuId) {
        Map<String, Object> map = new HashMap<>();
        // 根据id查询spu对象
        Spu spu = this.goodsClient.querySpuById(spuId);
        // 查询spuDetail
        SpuDetail spuDetail = this.goodsClient.querySpuDetailBySpuId(spuId);
        // 查询sku集合
        List<Sku> skus = this.goodsClient.querySkusBySpuId(spuId);
        // 查询分类
        List<Long> idList = Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3());
        List<String> categoryNames = this.categoryClient.queryNamesByIds(idList);
        List<Map<String, Object>> categories = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put("id", idList.get(i));
            categoryMap.put("name", categoryNames.get(i));
            categories.add(categoryMap);
        }
        // 查询品牌
        Brand brand = this.brandClient.queryBrandById(spu.getBrandId());
        // 查询规格参数组
        List<SpecGroup> specGroups = this.specificationClient.querySpecsByCid(spu.getCid3());
        // 查询特殊的规格参数
        List<SpecParam> specParams = this.specificationClient.listParams(null, spu.getCid3(), false, null);
        Map<Long, Object> paramMap = new HashMap<>();
        specParams.forEach(param -> paramMap.put(param.getId(), param.getName()));

        // 查询通用的规格参数
        List<SpecParam> genericSpecParams = this.specificationClient.listParams(null, spu.getCid3(), true, null);
        Map<Long, Object> genericParamMap = new HashMap<>();
        genericSpecParams.forEach(param -> genericParamMap.put(param.getId(), param.getName()));
        // 封装spu
        map.put("spu", spu);
        // 封装spuDetail
        map.put("spuDetail", spuDetail);
        // 封装sku集合
        map.put("skus", skus);
        // 分类
        map.put("categories", categories);
        // 品牌
        map.put("brand", brand);
        // 规格参数组
        map.put("groups", specGroups);
        // 查询特殊规格参数
        map.put("paramMap", paramMap);
        //查询通用规格参数
        map.put("genericParamMap", genericParamMap);
        return map;
    }
}
