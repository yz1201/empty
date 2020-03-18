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


    @Override
    public Category queryCategoryById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Category queryCategoryByName(String name) {
        Category category = new Category();
        category.setName(name);
        return mapper.selectOne(category);
    }

    @Override
    public Category updateCategory(Long id, String name) {
        Category target = this.queryCategoryById(id);
        target.setName(name);
        mapper.updateByPrimaryKey(target);
        return target;
    }

    @Override
    public void addSubCategory(Category category) {
        mapper.insert(category);
        Category parent = mapper.selectByPrimaryKey(category.getParentId());
        //子节点添加后，处理父节点的属性值。
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
            mapper.updateByPrimaryKey(parent);
        }
    }

    @Override
    public void deleteCurrentCategory(Long id) {
        Category category = new Category();
        category.setId(id);
        mapper.delete(category);
    }

}
