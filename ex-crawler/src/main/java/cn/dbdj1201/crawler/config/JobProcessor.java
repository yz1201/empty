package cn.dbdj1201.crawler.config;

import cn.dbdj1201.crawler.pojo.JobInfo;
import cn.dbdj1201.crawler.utils.MathSalary;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-19 17:19
 **/
@Component
public class JobProcessor implements PageProcessor {
    @Autowired
    private SpringDataPipeline pipeline;

    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 100)
    public void process() {
        //访问入口url地址
        String url = "https://search.51job.com/list/000000,000000,0000,01%252C32,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
        Spider.create(new JobProcessor())
                .addUrl(url)
                .addPipeline(this.pipeline)
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                .thread(8)
                .run();
    }

    @Override
    public void process(Page page) {
        //获取页面数据
        List<Selectable> nodes = page.getHtml().$("div#resultList div.el").nodes();

        //判断nodes是否为空
        if (nodes.isEmpty()) {
            try {
                //如果为空，表示这是招聘信息详情页保存信息详情
                this.saveJobInfo(page);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            //如果有值，表示这是招聘信息列表页
            for (Selectable node : nodes) {
                //获取招聘信息详情页url
                String jobUrl = node.links().toString();
                //添加到url任务列表中，等待下载
                page.addTargetRequest(jobUrl);

                //获取翻页按钮的超链接
                List<String> listUrl = page.getHtml().$("div.p_in li.bk").links().all();
                //添加到任务列表中
                page.addTargetRequests(listUrl);

            }
        }
    }

    private void saveJobInfo(Page page) {
        //创建招聘信息对象
        JobInfo jobInfo = new JobInfo();
        Html html = page.getHtml();

        //公司名称
        jobInfo.setCompanyName(html.$("div.tHeader p.cname a", "text").toString());
        //公司地址
        jobInfo.setCompanyAddr(html.$("div.bmsg > p.fp", "text").toString());
        //公司信息
        jobInfo.setCompanyInfo(html.$("div.tmsg", "text").toString());
        //职位名称
        jobInfo.setJobName(html.$("div.tHeader > div.in > div.cn > h1", "text").toString());
        //工作地点
        jobInfo.setJobAddr(html.$("div.bmsg > p.fp", "text").toString());
        //职位信息
        jobInfo.setJobInfo(Jsoup.parse(html.$("div.tBorderTop_box:nth-child(2)").toString()).text());
        //工资范围
        String salaryStr = html.$("div.tHeader > div.in > div.cn > strong", "text").toString();
        jobInfo.setSalaryMin(MathSalary.getSalary(salaryStr)[0]);
        jobInfo.setSalaryMax(MathSalary.getSalary(salaryStr)[1]);
        //职位详情url
        jobInfo.setUrl(page.getUrl().toString());

        //职位发布时间
        String time = html.$("div.tHeader > div.in > div.cn  p", "title").regex(".*发布").toString();
        String[] split = time.split("\\|");
        time = split[split.length - 1];
        jobInfo.setTime(time.substring(0, time.length() - 2));

        //保存数据
        page.putField("jobInfo", jobInfo);
    }

    @Override
    public Site getSite() {
        return Site.me()
//                .setCharset("utf8")
                .setTimeOut(10 * 1000)
                .setRetryTimes(3)
                .setRetrySleepTime(3000)
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Cookie", "guid=6fa36dbab364d6b6e71cd424290b9d7b; " +
                        "51job=cenglish%3D0%26%7C%26; " +
                        "nsearch=jobarea%3D%26%7C%26ord_field%3D%26%7C%26recentSearch0%3D%26%7C%26recentSearch1%3D%26%7C%26recentSearch2%3D%26%7C%26recentSearch3%3D%26%7C%26recentSearch4%3D%26%7C%26collapse_expansion%3D;" +
                        " search=jobarea%7E%60000000%7C%21ord_field%7E%600%7C%21recentSearch0%7E%60000000%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA01%2C32%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21")
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36");
    }
}
