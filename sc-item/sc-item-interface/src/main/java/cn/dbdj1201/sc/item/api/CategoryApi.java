package cn.dbdj1201.sc.item.api;

import cn.dbdj1201.sc.item.pojo.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-25 22:23
 **/
@RequestMapping("/category")
public interface CategoryApi {

    /**
     * 根据分类id查询分类名称
     *
     * @param ids
     * @return
     */
    @GetMapping("names")
    List<String> queryNamesByIds(@RequestParam("ids") List<Long> ids);

//    List<Category> queryAllByCid3(@RequestParam("id") Long cid3);
}
