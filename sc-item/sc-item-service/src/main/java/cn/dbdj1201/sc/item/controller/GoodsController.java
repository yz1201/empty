package cn.dbdj1201.sc.item.controller;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.item.bo.SpuBo;
import cn.dbdj1201.sc.item.pojo.Sku;
import cn.dbdj1201.sc.item.pojo.SpuDetail;
import cn.dbdj1201.sc.item.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-20 23:05
 **/
@Controller
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    /**
     * 根据条件分页查询
     *
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("spu/page")
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) Boolean saleable,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer rows
    ) {
        PageResult<SpuBo> result = this.goodsService.querySpuByPage(key, saleable, page, rows);
        if (result == null || CollectionUtils.isEmpty(result.getItems()))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }


    @GetMapping("spu/detail/{spuId}")
    public ResponseEntity<SpuDetail> editGoods(@PathVariable Long spuId) {
        SpuDetail spuDetail = goodsService.querySpuDetailBySpuId(spuId);
        if (spuDetail == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(spuDetail);
    }

    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkusBySpuId(@RequestParam("id") Long spuId) {
        List<Sku> skus = goodsService.querySkusBySpuId(spuId);
        if (CollectionUtils.isEmpty(skus))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(skus);
    }

    /**
     * 新增商品
     *
     * @param spuBo
     * @return
     */
    @PostMapping("goods")
    public ResponseEntity<Void> addGoods(@RequestBody SpuBo spuBo) {
        goodsService.saveGoods(spuBo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("goods")
    public ResponseEntity<Void> editGoods(@RequestBody SpuBo spuBo) {
        goodsService.updateGoods(spuBo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
