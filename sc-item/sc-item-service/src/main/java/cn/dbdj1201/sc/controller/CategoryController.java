package cn.dbdj1201.sc.controller;

import cn.dbdj1201.sc.pojo.Category;
import cn.dbdj1201.sc.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-17 16:05
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    /**
     * 根据父节点id查询子节点
     *
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(value = "pid", defaultValue = "1") Long pid) {

        if (pid == null || pid < 0) {
            // 响应400，相当于ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = this.service.queryCategoriesByPid(pid);
        if (CollectionUtils.isEmpty(categories)) {
            // 响应404
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("add")
    public ResponseEntity<String> addCategory(
            @RequestParam String name,
            @RequestParam Long parentId,
            @RequestParam Integer sort,
            @RequestParam(value = "isParent", defaultValue = "false") Boolean isParent
    ) {
//        if (service.queryCategoryByName(name) != null) {
//            return ResponseEntity.badRequest().build();
//        }
        Category category = new Category();
        category.setName(name);
        category.setParentId(parentId);
        category.setSort(sort);
        category.setIsParent(isParent);
        service.addSubCategory(category);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    @GetMapping("edit")
    public ResponseEntity<Category> editCurrentCategory(
            @RequestParam Long id,
            @RequestParam String name
    ) {
        if (id == null || id < 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.updateCategory(id, name));
    }

    @GetMapping("delete")
    public ResponseEntity<String> deleteCurrentCategory(@RequestParam Long id) {
        if (id <= 1000)
            return ResponseEntity.badRequest().body("这个删不了吧？");
        service.deleteCurrentCategory(id);
        return ResponseEntity.ok().body("bye~");
    }

    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryCategoryByBid(@PathVariable Long bid) {
        return ResponseEntity.ok(service.queryCategoriesByBrandId(bid));
    }

}
