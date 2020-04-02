package cn.dbdj1201.sc.auth.service.impl;

import cn.dbdj1201.sc.auth.ScAuthApplication;
import cn.dbdj1201.sc.auth.service.IAuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-04-03 1:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScAuthApplication.class)
public class AuthServiceTest {

    @Autowired
    private IAuthService authService;

    @Test
    public void authentication() {
        String token = this.authService.authentication("", "");
        System.out.println(token);
    }
}