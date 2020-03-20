package cn.dbdj1201.sc.item.service.impl;

import cn.dbdj1201.sc.item.ScItemServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tyz1201
 * @datetime 2020-03-19 12:49
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScItemServiceApplication.class)
public class CategoryServiceTest {
    @Autowired
    private CategoryService service;

    @Test
    public void queryCategoriesByBrandId() {
        service.queryCategoriesByBrandId(325404L).forEach(System.out::println);
    }
}