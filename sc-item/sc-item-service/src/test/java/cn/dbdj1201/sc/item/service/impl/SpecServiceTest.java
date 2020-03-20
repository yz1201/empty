package cn.dbdj1201.sc.item.service.impl;

import cn.dbdj1201.sc.item.ScItemServiceApplication;
import cn.dbdj1201.sc.item.pojo.SpecGroup;
import cn.dbdj1201.sc.item.service.ISpecGroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-03-20 11:33
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScItemServiceApplication.class)
public class SpecServiceTest {

    @Autowired
    private ISpecGroupService specGroupService;

    @Test
    public void queryGroupsByCid() {
        specGroupService.queryGroupsByCid(76L).forEach(System.out::println);
    }
}