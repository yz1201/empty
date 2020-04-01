package cn.dbdj1201.sc.user.service.impl;

import cn.dbdj1201.sc.user.ScUserServiceApplication;
import cn.dbdj1201.sc.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-04-02 1:39
 **/
@SpringBootTest(classes = ScUserServiceApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void checkUser() {
        String data = "zhangsan";
        Integer type = 1;
        System.out.println(userService.checkUser(data, type));
    }
}