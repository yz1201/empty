package cn.dbdj1201.sc.item.service;

import cn.dbdj1201.sc.item.pojo.SpecParam;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-20 11:53
 **/
public interface ISpecParamService {
    /**
     * 根据组ID查询组信息
     *
     * @param gid
     * @return
     */
    List<SpecParam> queryParamsByGroupId(Long gid);
}
