package cn.dbdj1201.crawler.dao;

import cn.dbdj1201.crawler.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tyz1201
 * @datetime 2020-04-19 17:08
 **/
public interface IJobInfoDao extends JpaRepository<JobInfo, Long> {
}
