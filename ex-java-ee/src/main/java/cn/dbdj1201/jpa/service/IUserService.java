package cn.dbdj1201.jpa.service;

import cn.dbdj1201.jpa.pojo.User;

/**
 * @author tyz1201
 * @datetime 2020-04-11 15:47
 **/
public interface IUserService {
    void save(User user);

    User findById(Integer id);

    void updateUser(User user);

    void deleteById(Integer id);

}
