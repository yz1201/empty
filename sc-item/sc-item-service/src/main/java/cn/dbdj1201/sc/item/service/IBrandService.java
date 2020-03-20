package cn.dbdj1201.sc.item.service;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.item.pojo.Brand;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-17 22:33
 **/
public interface IBrandService {

    PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    void addBrand(Brand brand, List<Long> cids);

    void delete(Long bid);

    List<Brand> queryBrandsByCid(Long cid);
}
