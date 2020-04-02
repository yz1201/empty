package cn.dbdj1201.sc.user.service.impl;

import cn.dbdj120.sc.common.utils.CodecUtils;
import cn.dbdj1201.sc.user.ScUserServiceApplication;
import cn.dbdj1201.sc.user.pojo.User;
import cn.dbdj1201.sc.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
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

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void checkUser() {
        String data = "zhangsan";
        Integer type = 1;
        System.out.println(userService.checkUser(data, type));
    }

    @Test
    public void sendVerifyCode() {
        this.userService.sendVerifyCode("15957121194");
    }


    @Test
    public void testSend() throws InterruptedException {
        String msg = "hello, Spring boot amqp";
        this.amqpTemplate.convertAndSend("spring.test.exchange", "abc", msg);
        System.out.println("send msg -> " + msg);
    }

    @Test
    public void register() {
        User user = new User();
        user.setUsername("dbdj12011");
        user.setPassword("dbdj12011");
        user.setPhone("15957121194");
        this.userService.register(user, "992414");
    }

    @Test
    public void queryByUsernameAndPassword() {
        String username = "dbdj1201";
        String password = "dbdj1201";
        User user = this.userService.queryByUsernameAndPassword(username, password);

        System.out.println("user-> " + user);
        System.out.println("user-> " + CodecUtils.md5Hex(password, user.getSalt()));
    }
}