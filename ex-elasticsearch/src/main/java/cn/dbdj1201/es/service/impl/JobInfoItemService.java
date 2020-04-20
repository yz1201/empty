package cn.dbdj1201.es.service.impl;

import cn.dbdj1201.es.pojo.JobInfoItem;
import cn.dbdj1201.es.pojo.JobResult;
import cn.dbdj1201.es.repository.JobInfoItemRepository;
import cn.dbdj1201.es.service.IJobInfoItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-20 14:32
 **/
@Service
public class JobInfoItemService implements IJobInfoItemService {

    @Autowired
    private JobInfoItemRepository itemRepository;

    @Override
    public void save(JobInfoItem jobInfoField) {
        this.itemRepository.save(jobInfoField);
    }

    @Override
    public void saveAll(List<JobInfoItem> list) {
        this.itemRepository.saveAll(list);
    }

    @Override
    public JobResult search(String salary, String jobAddr, String keyword, Integer page) {
        //薪资处理 20-*
        int salaryMin, salaryMax;
        String[] salays = salary.split("-");
        //获取最小值
        if ("*".equals(salays[0])) {
            salaryMin = 0;
        } else {
            salaryMin = Integer.parseInt(salays[0]) * 10000;
        }

        //获取最大值
        if ("*".equals(salays[1])) {
            salaryMax = 900000000;
        } else {
            salaryMax = Integer.parseInt(salays[1]) * 10000;
        }

        //工作地址如果为空，就设置为*
        if (StringUtils.isBlank(jobAddr)) {
            jobAddr = "*";

            //查询关键词为空，就设置为*
        }   if (StringUtils.isBlank(keyword)) {
            keyword = "*";
        }


        //获取分页,设置每页显示30条数据
        Pageable pageable = PageRequest.of(page - 1, 30);

        //执行查询
        Page<JobInfoItem> pages = this.itemRepository
                .findBySalaryMinBetweenAndSalaryMaxBetweenAndJobAddrAndJobNameAndJobInfo(salaryMin,
                        salaryMax, salaryMin, salaryMax, jobAddr, keyword, keyword, pageable);

        //封装结果
        JobResult jobResult = new JobResult();
        jobResult.setRows(pages.getContent());
        jobResult.setPageTotal(pages.getTotalPages());

        return jobResult;
    }
}
