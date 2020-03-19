package cn.dbdj1201.sc.service.impl;

import cn.dbdj1201.sc.ScItemServiceApplication;
import cn.dbdj1201.sc.pojo.Brand;
import cn.dbdj1201.sc.service.IBrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * @author tyz1201
 * @datetime 2020-03-18 14:54
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScItemServiceApplication.class)
public class BrandServiceTest {

    @Autowired
    private IBrandService service;

    @Test
    public void queryBrandsByPage() {
        service.queryBrandsByPage("", 1, 30, "id", false).getItems().forEach(System.out::println);
    }

    @Test
    public void addBrand() {
        Brand brand = new Brand();
        brand.setImage("http://image.sc1.com/test1.jpg");
        brand.setLetter('A');
        brand.setName("test1");
        List<Long> longs = new ArrayList<>();
        longs.add(23333L);
        longs.add(23334L);
        longs.add(23335L);
        longs.add(23336L);
        service.addBrand(brand, longs);
    }

    @Test
    public void delete() {
        service.delete(325403L);
    }
}