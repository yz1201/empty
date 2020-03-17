package cn.dbdj1201.sc.service.impl;

import cn.dbdj1201.sc.mapper.CategoryMapper;
import cn.dbdj1201.sc.pojo.Category;
import cn.dbdj1201.sc.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-17 16:00
 **/
@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryMapper mapper;

    /**
     * @param pid
     * @return
     */
    @Override
    public List<Category> queryCategoriesByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        return mapper.select(category);
    }
}
