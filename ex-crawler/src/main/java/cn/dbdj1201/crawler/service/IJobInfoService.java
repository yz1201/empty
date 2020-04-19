package cn.dbdj1201.crawler.service;

import cn.dbdj1201.crawler.pojo.JobInfo;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-19 17:08
 **/
public interface IJobInfoService {

    /**
     * 保存
     *
     * @param jobInfo
     */
    void save(JobInfo jobInfo);


    /**
     * 条件查询全部
     *
     * @return
     */
    List<JobInfo> findJobInfo(JobInfo jobInfo);
}
