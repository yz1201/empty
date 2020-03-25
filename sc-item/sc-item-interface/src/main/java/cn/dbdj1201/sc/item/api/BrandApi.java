package cn.dbdj1201.sc.item.api;

import cn.dbdj1201.sc.item.pojo.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tyz1201
 * @datetime 2020-03-25 22:23
 **/
@RequestMapping("/brand")
public interface BrandApi {
    /**
     * 根据品牌id查询出该品牌
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    Brand queryBrandById(@PathVariable Long id);
}
