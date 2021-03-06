package cn.dbdj1201.sc.item.controller;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.item.pojo.Brand;
import cn.dbdj1201.sc.item.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param key    搜索关键字
     * @param page   当前页码
     * @param rows   当面页数据行数
     * @param sortBy 根据哪个字段排序
     * @param desc   是否降序
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

    @PostMapping
    public ResponseEntity<Void> addBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.addBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("delete/{bid}")
    public ResponseEntity<String> deleteBrand(@PathVariable Long bid) {

        if (bid <= 325400)
            return ResponseEntity.badRequest().body("删了去哪找回来去，删你自己写的");

        return ResponseEntity.ok("已经删除了");
    }

    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable Long cid) {
        List<Brand> brands = brandService.queryBrandsByCid(cid);
        if (CollectionUtils.isEmpty(brands))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("{id}")
    public ResponseEntity<Brand> queryBrandById(@PathVariable Long id) {
        Brand brand = this.brandService.queryBrandById(id);
        if (brand == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(brand);
    }

}
