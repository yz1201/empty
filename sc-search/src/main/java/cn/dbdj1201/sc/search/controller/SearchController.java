package cn.dbdj1201.sc.search.controller;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.item.pojo.Category;
import cn.dbdj1201.sc.item.pojo.Spu;
import cn.dbdj1201.sc.search.pojo.Goods;
import cn.dbdj1201.sc.search.pojo.SearchRequest;
import cn.dbdj1201.sc.search.service.IIndexService;
import cn.dbdj1201.sc.search.service.ISearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-25 23:56
 **/
//@RequestMapping("/search")
@Controller
public class SearchController {

    @Autowired
    private ISearchService searchService;
    @Autowired
    private IIndexService indexService;

    @GetMapping("list")
    public ResponseEntity<Goods> listGoods() throws JsonProcessingException {
        Spu spu = new Spu();
        spu.setId(2L);
        spu.setBrandId(8557L);
        spu.setCid1(74L);
        spu.setCid2(75L);
        spu.setCid3(76L);
        spu.setCreateTime(new Date());
        spu.setTitle("华为 G9 青春版 ");
        spu.setSubTitle("骁龙芯片！3GB运行内存！索尼1300万摄像头！<a href='https://sale.jd.com/act/DhKrOjXnFcGL.html' target='_blank'>华为新品全面上线，更多优惠猛戳》》</a>");
        return ResponseEntity.ok(this.searchService.buildGoods(spu));
    }

    /**
     * 创建索引库，轻易勿动。
     *
     * @return
     */
    @GetMapping("createIndex/create/index/put/mapping/dont/touch/this/interface/ok")
    public ResponseEntity<Void> createIndex() {
        indexService.saveGoods();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("page")
    public ResponseEntity<PageResult<Goods>> search(@RequestBody SearchRequest searchRequest) {
        PageResult<Goods> pageResult = this.searchService.search(searchRequest);
        if (CollectionUtils.isEmpty(pageResult.getItems()))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pageResult);
    }

}
