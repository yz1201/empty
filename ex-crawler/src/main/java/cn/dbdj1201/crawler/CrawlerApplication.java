package cn.dbdj1201.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author tyz1201
 * @datetime 2020-04-16 22:06
 **/
@SpringBootApplication
//设置开启定时任务
@EnableScheduling
public class CrawlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class, args);
    }
}
