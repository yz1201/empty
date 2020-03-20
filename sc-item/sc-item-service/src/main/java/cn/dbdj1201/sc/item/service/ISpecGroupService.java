package cn.dbdj1201.sc.item.service;

import cn.dbdj1201.sc.item.pojo.SpecGroup;
import cn.dbdj1201.sc.item.pojo.SpecParam;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-20 11:28
 **/
public interface ISpecGroupService {

    /**
     * 根据商品分类ID查询出包含的商品spec groups
     * @param cid
     * @return
     */
    List<SpecGroup> queryGroupsByCid(Long cid);

    void addGroup(Long cid, String name);

    void deleteGroup(Long id);

    void editGroup(SpecGroup specGroup);

}
