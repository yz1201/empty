package cn.dbdj1201.sc.item.service.impl;

import cn.dbdj1201.sc.item.mapper.SpecGroupMapper;
import cn.dbdj1201.sc.item.mapper.SpecParamMapper;
import cn.dbdj1201.sc.item.pojo.SpecGroup;
import cn.dbdj1201.sc.item.pojo.SpecParam;
import cn.dbdj1201.sc.item.service.ISpecGroupService;
import cn.dbdj1201.sc.item.service.ISpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-20 11:31
 **/
@Service
public class SpecService implements ISpecGroupService, ISpecParamService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    @Override
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup group = new SpecGroup();
        group.setCid(cid);
        return groupMapper.select(group);
    }

    @Override
    public List<SpecParam> queryParamsByGroupId(Long gid) {
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        return paramMapper.select(param);
    }
}
