package cn.dbdj1201.sc.item.service;

import cn.dbdj1201.sc.item.pojo.SpecParam;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-20 11:53
 **/
public interface ISpecParamService {
//    /**
//     * 根据组ID查询组信息
//     *
//     * @param gid
//     * @return
//     */
//    List<SpecParam> queryParamsByGroupId(Long gid);
//    List<SpecParam> queryParamsByCid(Long cid);

    void editParam(SpecParam specParam);

    void addParam(SpecParam specParam);

    void deleteParam(Long id);


    /**
     * 多字段条件查询
     * @param gid
     * @param cid
     * @param generic
     * @param searching
     * @return
     */
    List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching);
}
