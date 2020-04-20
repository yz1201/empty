package cn.dbdj1201.es.repository;

import cn.dbdj1201.es.pojo.JobInfoItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author tyz1201
 * @datetime 2020-04-20 14:31
 **/
public interface JobInfoItemRepository extends ElasticsearchRepository<JobInfoItem, Long> {

    /**
     * 根据条件分页查询数据
     *
     * @param salaryMin1 薪资下限最小值
     * @param salaryMin2 薪资下限最高值
     * @param salaryMax1 薪资上限最小值
     * @param salaryMax2 薪资上限最大值
     * @param jobAddr    工作地点
     * @param jobName    职位名称
     * @param jobInfo    职位信息
     * @param pageable   分页数据
     * @return 分页结果集
     */
    Page<JobInfoItem> findBySalaryMinBetweenAndSalaryMaxBetweenAndJobAddrAndJobNameAndJobInfo(Integer salaryMin1, Integer salaryMin2,
                                                                                              Integer salaryMax1, Integer salaryMax2,
                                                                                              String jobAddr, String jobName,
                                                                                              String jobInfo, Pageable pageable);
}
