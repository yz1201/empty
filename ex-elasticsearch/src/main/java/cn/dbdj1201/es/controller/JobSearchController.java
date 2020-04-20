package cn.dbdj1201.es.controller;

import cn.dbdj1201.es.pojo.JobResult;
import cn.dbdj1201.es.service.IJobInfoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tyz1201
 * @datetime 2020-04-20 14:35
 **/
@RestController
public class JobSearchController {

    @Autowired
    private IJobInfoItemService itemService;

    /**
     * 根据条件分页查询
     *
     * @param salary
     * @param jobaddr
     * @param keyword
     * @param page
     * @return
     */
    @PostMapping("/search")
    public JobResult search(String salary, String jobaddr, String keyword, Integer page) {
        return this.itemService.search(salary, jobaddr, keyword, page);
    }
}
