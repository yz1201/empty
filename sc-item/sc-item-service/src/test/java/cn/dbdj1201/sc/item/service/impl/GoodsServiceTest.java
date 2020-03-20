package cn.dbdj1201.sc.item.service.impl;

import cn.dbdj1201.sc.item.ScItemServiceApplication;
import cn.dbdj1201.sc.item.service.IGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-03-20 23:53
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScItemServiceApplication.class)
public class GoodsServiceTest {

    @Autowired
    private IGoodsService service;

    @Test
    public void querySpuByPage() {
        service.querySpuByPage("芯片", null, 1, 10).getItems().forEach(System.out::println);
        System.out.println(service.querySpuByPage("芯片", null, 1, 10));
    }

    @Test
    public void testQuerySpuByPage() {
    }

    @Test
    public void saveGoods() {
    }

    @Test
    public void querySpuDetailBySpuId() {
        System.out.println(service.querySpuDetailBySpuId(2L));
    }

    @Test
    public void querySkusBySpuId() {
        service.querySkusBySpuId(2L).forEach(System.out::println);
    }
}