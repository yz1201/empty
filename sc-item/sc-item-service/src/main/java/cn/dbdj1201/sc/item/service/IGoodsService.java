package cn.dbdj1201.sc.item.service;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.item.bo.SpuBo;
import cn.dbdj1201.sc.item.pojo.Sku;
import cn.dbdj1201.sc.item.pojo.Spu;
import cn.dbdj1201.sc.item.pojo.SpuDetail;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-20 23:04
 **/
public interface IGoodsService {
    /**
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows);

    void saveGoods(SpuBo spuBo);

    SpuDetail querySpuDetailBySpuId(Long spuId);

    List<Sku> querySkusBySpuId(Long spuId);

    void updateGoods(SpuBo spuBo);

    Spu querySpuById(Long id);

    Sku querySkuById(Long id);
}
