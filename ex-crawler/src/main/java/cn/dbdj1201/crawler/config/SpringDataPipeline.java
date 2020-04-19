package cn.dbdj1201.crawler.config;

import cn.dbdj1201.crawler.pojo.JobInfo;
import cn.dbdj1201.crawler.service.IJobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author tyz1201
 * @datetime 2020-04-19 17:25
 **/
@Component
public class SpringDataPipeline implements Pipeline {

    @Autowired
    private IJobInfoService jobInfoService;

    /**
     * 处理key为"jobInfo"的数据流，保存到数据库
     * @param resultItems resultItems
     * @param task task
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取需要保存到MySQL的数据
        JobInfo jobInfo = resultItems.get("jobInfo");
        //判断获取到的数据不为空
        if (jobInfo != null) {
            //如果有值则进行保存
            this.jobInfoService.save(jobInfo);
        }
    }
}
