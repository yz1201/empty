package cn.dbdj1201.goods.web.client;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.item.api.GoodsApi;
import cn.dbdj1201.sc.item.bo.SpuBo;
import cn.dbdj1201.sc.item.pojo.Sku;
import cn.dbdj1201.sc.item.pojo.SpuDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-25 17:39
 **/
@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {


    /**
     * 根据条件分页查询
     *
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("spu/page")
    PageResult<SpuBo> querySpuByPage(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) Boolean saleable,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer rows
    );

    /**
     * 根据spuID查询spuDetail
     *
     * @param spuId
     * @return
     */
    @RequestMapping("spu/detail/{spuId}")
    SpuDetail querySpuDetailBySpuId(@PathVariable("spuId") Long spuId);

    /**
     * 根据spuID查询包含的sku
     *
     * @param spuId
     * @return
     */
    @RequestMapping("sku/list")
    List<Sku> querySkusBySpuId(@RequestParam("id") Long spuId);

}
