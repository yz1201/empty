package cn.dbdj1201.goods.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tyz1201
 * @datetime 2020-03-30 11:48
 **/
@Controller
@RequestMapping("/item")
public class GoodsController {

    @GetMapping("{id}.html")
    public String getDetails(@PathVariable("id") Long id) {
        System.out.println("i am ready!->"+id);
        return "item";
    }
}
