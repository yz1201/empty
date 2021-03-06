package cn.dbdj1201.sc.item.service.impl;

import cn.dbdj1201.sc.item.service.ICategoryService;
import cn.dbdj1201.sc.item.mapper.CategoryMapper;
import cn.dbdj1201.sc.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public List<Category> queryCategoriesByBrandId(Long bid) {
        List<Category> list = new ArrayList<>();
        mapper.queryCategoryIdByBrandID(bid).forEach(cid -> list.add(mapper.selectByPrimaryKey(cid)));
        return list;
    }

    @Override
    public List<String> queryNamesByCids(List<Long> cids) {
        List<Category> categories = mapper.selectByIdList(cids);
//        cids.forEach(cid -> names.add(mapper.selectByPrimaryKey(cid).getName()));
//        mapper.selectByIdList(cids).forEach(category -> names.add(category.getName()));
        return categories.stream().map(Category::getName).collect(Collectors.toList());
    }

    @Override
    public List<Category> queryAllByCid3(Long cid3) {
        Category c3 = this.mapper.selectByPrimaryKey(cid3);
        Category c2 = this.mapper.selectByPrimaryKey(c3.getParentId());
        Category c1 = this.mapper.selectByPrimaryKey(c2.getParentId());
        return Arrays.asList(c1, c2, c3);
    }

}
