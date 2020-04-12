package cn.dbdj1201.jpa.service.impl;

import cn.dbdj1201.jpa.dao.IRoleDao;
import cn.dbdj1201.jpa.dao.IUserDao;
import cn.dbdj1201.jpa.pojo.Role;
import cn.dbdj1201.jpa.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-12 14:31
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MultiQuery2Test {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;

    @Transactional  //开启事务
    @Rollback(false)//设置为不回滚
    @Test
    public void testAdd() {
//        User user = new User();
//        Role role = new Role();
//        user.setName("user1");
//        role.setRoleName("role1");
//        List<User> users = new ArrayList<>();
//        List<Role> roles = new ArrayList<>();
//        users.add(user);
//        roles.add(role);
//        user.setRoles(roles);
//        role.setUsers(users);
//        this.userDao.save(user);
//        this.roleDao.save(role);

//        User user = new User();
//        user.setName("tyz1201");
//        user.setAge("25");
//        this.userDao.save(user);

        this.userDao.deleteById(8);
    }

}
