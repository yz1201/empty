package cn.dbdj1201.crawler.service.impl;

import cn.dbdj1201.crawler.dao.IJobInfoDao;
import cn.dbdj1201.crawler.pojo.JobInfo;
import cn.dbdj1201.crawler.service.IJobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-19 17:09
 **/
@Service
public class JobInfoService implements IJobInfoService {

    @Autowired
    private IJobInfoDao jobInfoDao;

    @Override
    @Transactional
    public void save(JobInfo jobInfo) {
        if (jobInfo == null)
            throw new RuntimeException("出问题了");
        //先从数据库查询数据,根据发布日期查询和url查询
        JobInfo example = new JobInfo();
        example.setTime(jobInfo.getTime());
        example.setUrl(jobInfo.getUrl());
        List<JobInfo> infoList = this.findJobInfo(example);
        //没有查询到数据则新增或者修改数据
        if (infoList.size() == 0)
            this.jobInfoDao.save(jobInfo);
    }

    @Override
    public List<JobInfo> findJobInfo(JobInfo jobInfo) {
        return this.jobInfoDao.findAll(Example.of(jobInfo));
    }
}
