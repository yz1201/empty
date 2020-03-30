package cn.dbdj1201.goods.web.controller;

import cn.dbdj1201.goods.web.service.IGoodsHtmlService;
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

    @Autowired
    private IGoodsHtmlService goodsHtmlService;

    /**
     * 跳转到商品详情页
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("{id}.html")
    public String toItemPage(@PathVariable("id") Long id, Model model) {
        // 加载所需的数据
        Map<String, Object> map = this.goodsService.loadData(id);
        // 把数据放入数据模型
        model.addAllAttributes(map);
        // 页面静态化
        this.goodsHtmlService.asyncExecute(id);
        return "item";
    }
}
