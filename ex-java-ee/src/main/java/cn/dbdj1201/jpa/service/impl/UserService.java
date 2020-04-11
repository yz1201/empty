package cn.dbdj1201.jpa.service.impl;

import cn.dbdj1201.jpa.pojo.User;
import cn.dbdj1201.jpa.service.IUserService;
import cn.dbdj1201.jpa.utils.JpaUtils;

/**
 * @author tyz1201
 * @datetime 2020-04-11 15:47
 **/
public class UserService implements IUserService {
    private JpaUtils jpaUtils = new JpaUtils();

    @Override
    public void save(User user) {

    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
