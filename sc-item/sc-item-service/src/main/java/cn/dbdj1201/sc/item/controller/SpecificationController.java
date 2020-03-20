package cn.dbdj1201.sc.item.controller;

import cn.dbdj1201.sc.item.pojo.SpecGroup;
import cn.dbdj1201.sc.item.pojo.SpecParam;
import cn.dbdj1201.sc.item.service.ISpecGroupService;
import cn.dbdj1201.sc.item.service.ISpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> listParamsByGroupId(@RequestParam("gid") Long gid) {
        List<SpecParam> params = paramService.queryParamsByGroupId(gid);
        if (CollectionUtils.isEmpty(params))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(params);
    }
}
