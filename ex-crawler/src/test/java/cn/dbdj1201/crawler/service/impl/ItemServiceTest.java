package cn.dbdj1201.crawler.service.impl;

import cn.dbdj1201.crawler.CrawlerApplication;
import cn.dbdj1201.crawler.pojo.Item;
import cn.dbdj1201.crawler.service.IItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Id;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-04-16 22:08
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrawlerApplication.class)
public class ItemServiceTest {

    @Autowired
    private IItemService itemService;

    @Test
    public void findAll() {
        Item item = new Item();
        item.setTitle("冲就完事了");
        this.itemService.findAll(item).forEach(System.out::println);
    }

    @Test
    public void save() {
        for (int i = 0; i < 100; i++) {
            Item item = new Item();
            item.setPic("YSJ <-> " + i);
            item.setCreated(new Date());
            item.setPrice(250d + (double) i);
            item.setSku(1243L + i);
            item.setSpu(12345L + i);
            item.setTitle("冲就完事了 -> " + i);
            item.setUpdated(new Date());
            item.setUrl("http://www.hupu.com?id=" + i);
            this.itemService.save(item);
        }

    }
}