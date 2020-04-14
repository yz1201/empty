package cn.dbdj1201.user.ms.service;

import cn.dbdj1201.user.ms.UserMSApplication;
import cn.dbdj1201.user.ms.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-04-14 16:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserMSApplication.class)
public class IUserServiceTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void userRegister() {

    }

    @Test
    public void findAll() {
        this.userMapper.selectAll().forEach(System.out::println);
    }

    @Test
    public void findUserById() {
    }

    @Test
    public void modifyUserInfo() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void deleteByIds() {
    }
}