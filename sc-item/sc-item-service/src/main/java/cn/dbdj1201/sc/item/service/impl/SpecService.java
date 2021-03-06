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
import java.util.stream.Collectors;

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
    public void addGroup(Long cid, String name) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        specGroup.setName(name);
        groupMapper.insertSelective(specGroup);
    }

    @Override
    public void deleteGroup(Long id) {
        groupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void editGroup(SpecGroup specGroup) {
        groupMapper.updateByPrimaryKeySelective(specGroup);
    }

    @Override
    public List<SpecGroup> querySpecsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> specGroups = this.groupMapper.select(specGroup);
        return specGroups.stream().peek(group -> {
            SpecParam record = new SpecParam();
            record.setCid(cid);
            record.setGroupId(group.getId());
            group.setParams(this.paramMapper.select(record));
        }).collect(Collectors.toList());
    }

//    @Override
//    public List<SpecParam> queryParamsByGroupId(Long gid) {
//        SpecParam param = new SpecParam();
//        param.setGroupId(gid);
//        return paramMapper.select(param);
//    }

    @Override
    public void editParam(SpecParam specParam) {
        paramMapper.updateByPrimaryKeySelective(specParam);
    }

    @Override
    public void addParam(SpecParam specParam) {
        paramMapper.insertSelective(specParam);
    }

    @Override
    public void deleteParam(Long id) {
        paramMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        param.setCid(cid);
        param.setGeneric(generic);
        param.setSearching(searching);
        return paramMapper.select(param);
    }

//    @Override
//    public List<SpecParam> queryParamsByCid(Long cid) {
//        SpecParam record = new SpecParam();
//        record.setCid(cid);
//        return paramMapper.select(record);
//    }
}
