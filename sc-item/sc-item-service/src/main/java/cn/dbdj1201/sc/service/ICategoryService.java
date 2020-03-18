package cn.dbdj1201.sc.service;

import cn.dbdj1201.sc.pojo.Category;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-17 15:57
 **/
public interface ICategoryService {

    List<Category> queryCategoriesByPid(Long pid);

    Category queryCategoryById(Long id);

    Category queryCategoryByName(String name);

    Category updateCategory(Long id, String name);

    void addSubCategory(Category category);

    void deleteCurrentCategory(Long id);
}
