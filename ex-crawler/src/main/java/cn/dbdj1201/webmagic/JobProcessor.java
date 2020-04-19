package cn.dbdj1201.webmagic;

import cn.dbdj1201.crawler.pojo.JobInfo;
import cn.dbdj1201.crawler.utils.MathSalary;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * @author tyz1201
 * @datetime 2020-04-19 15:32
 **/
public class JobProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        String title = page.getHtml().css("div.bmsg > p.fp", "text").toString();
        System.out.println(title);
//        page.putField("links", title);
    }

    private final Site site = Site.me()
            .addHeader("Connection", "Keep-Alive")
            .addHeader("Cookie", "guid=6fa36dbab364d6b6e71cd424290b9d7b; " +
                    "51job=cenglish%3D0%26%7C%26; " +
                    "nsearch=jobarea%3D%26%7C%26ord_field%3D%26%7C%26recentSearch0%3D%26%7C%26recentSearch1%3D%26%7C%26recentSearch2%3D%26%7C%26recentSearch3%3D%26%7C%26recentSearch4%3D%26%7C%26collapse_expansion%3D;" +
                    " search=jobarea%7E%60000000%7C%21ord_field%7E%600%7C%21recentSearch0%7E%60000000%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA01%2C32%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36");


    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
        Spider.create(new JobProcessor())
                .addUrl("https://jobs.51job.com/hangzhou/118361764.html?s=01&t=0")
                .run();

    }

}
