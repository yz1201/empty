package cn.dbdj1201.goods.web.controller;

import cn.dbdj1201.goods.web.service.IGoodsService;
import cn.dbdj1201.goods.web.service.impl.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author tyz1201
 * @datetime 2020-03-30 11:48
 **/
@Controller
@RequestMapping("/item")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    /**
     * 跳转到商品详情页
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("{id}.html")
    public String toItemPage(Model model, @PathVariable("id") Long id) {
        // 加载所需的数据
        Map<String, Object> modelMap = this.goodsService.loadData(id);
//        modelMap.forEach((k, v) -> System.out.println(k + "->" + v));
        // 放入模型
        model.addAllAttributes(modelMap);
        return "item";
    }
}
