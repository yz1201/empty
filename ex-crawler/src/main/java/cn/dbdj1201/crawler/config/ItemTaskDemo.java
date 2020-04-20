package cn.dbdj1201.crawler.config;

/**
 * @author tyz1201
 * @datetime 2020-04-20 10:35
 **/

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class ItemTaskDemo {

    @Scheduled(cron = "0/59 * * * * *")
    public void test() {
        System.out.println(LocalDateTime.now() + " am i handsome?");
    }
}
