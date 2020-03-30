package cn.dbdj1201.sc.item.api;

import cn.dbdj1201.sc.item.pojo.SpecGroup;
import cn.dbdj1201.sc.item.pojo.SpecParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-25 22:24
 **/
@RequestMapping("/spec")
public interface SpecificationApi {

    /**
     * 查询该分类下的参数组
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public List<SpecGroup> listGroups(@PathVariable Long cid);

    /**
     * 根据条件查询规格参数
     *
     * @param gid       规格组ID
     * @param cid       分类ID
     * @param generic   是否为通用参数
     * @param searching 是否为可搜索字段
     * @return
     */
    @GetMapping("params")
    List<SpecParam> listParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "generic", required = false) Boolean generic,
            @RequestParam(value = "searching", required = false) Boolean searching
    );

    // 查询规格参数组，及组内参数
    @GetMapping("{cid}")
    List<SpecGroup> querySpecsByCid(@PathVariable("cid") Long cid);
}
