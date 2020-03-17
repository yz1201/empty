package cn.dbdj1201.sc.controller;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.pojo.Brand;
import cn.dbdj1201.sc.service.IBrandService;
import cn.dbdj1201.sc.service.impl.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tyz1201
 * @datetime 2020-03-17 22:32
 **/
@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    /**
     * 根据查询条件分页并排序查询品牌信息
     *
     * @param key 搜索关键字
     * @param page 当前页码
     * @param rows 当面页数据行数
     * @param sortBy 根据什么排序
     * @param desc 是否降序
     * @return 分页结果集
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc
    ) {
        PageResult<Brand> result = this.brandService.queryBrandsByPage(key, page, rows, sortBy, desc);
        if (CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
