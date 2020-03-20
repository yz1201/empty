package cn.dbdj1201.sc.item.controller;

import cn.dbdj1201.sc.item.pojo.SpecGroup;
import cn.dbdj1201.sc.item.pojo.SpecParam;
import cn.dbdj1201.sc.item.service.ISpecGroupService;
import cn.dbdj1201.sc.item.service.ISpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-20 11:27
 **/
@Controller
@RequestMapping("/spec")
public class SpecificationController {

    @Autowired
    private ISpecGroupService groupService;

    @Autowired
    private ISpecParamService paramService;

    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> listGroups(@PathVariable Long cid) {
        List<SpecGroup> groups = groupService.queryGroupsByCid(cid);
        if (CollectionUtils.isEmpty(groups))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(groups);
    }

    @PostMapping("group")
    public ResponseEntity<Void> addGroup(@RequestParam("cid") Long cid, @RequestParam("name") String name) {
        groupService.addGroup(cid, name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("group")
    public ResponseEntity<Void> editGroup(SpecGroup specGroup) {
        groupService.editGroup(specGroup);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("group/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> listParamsByGroupId(@RequestParam("gid") Long gid) {
        List<SpecParam> params = paramService.queryParamsByGroupId(gid);
        if (CollectionUtils.isEmpty(params))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(params);
    }


    @PutMapping("param")
    public ResponseEntity<Void> editParam(SpecParam specParam) {
        paramService.editParam(specParam);
        return ResponseEntity.ok().build();
    }

    @PostMapping("param")
    public ResponseEntity<Void> addParam(SpecParam specParam) {
       paramService.addParam(specParam);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("param/{id}")
    public ResponseEntity<Void> deleteParam(@PathVariable Long id) {
        paramService.deleteParam(id);
        return ResponseEntity.ok().build();
    }


}
