package cn.dbdj1201.es.service;

import cn.dbdj1201.es.pojo.JobInfoItem;
import cn.dbdj1201.es.pojo.JobResult;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-20 14:31
 **/
public interface IJobInfoItemService {

    /**
     * 保存一条数据
     *
     * @param jobInfoField
     */
    void save(JobInfoItem jobInfoField);

    /**
     * 批量保存数据
     *
     * @param list
     */
    void saveAll(List<JobInfoItem> list);

    JobResult search(String salary, String jobaddr, String keyword, Integer page);
}
