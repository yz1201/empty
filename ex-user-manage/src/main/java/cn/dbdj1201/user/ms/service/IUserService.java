package cn.dbdj1201.user.ms.service;

import cn.dbdj1201.user.ms.pojo.User;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-14 15:57
 **/
public interface IUserService {
    /**
     * 用户注册/新增
     *
     * @param user
     */
    void userRegister(User user);

    /**
     * 查找所有用户
     *
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 修改用户信息
     *
     * @param user
     */
    void modifyUserInfo(User user);

    /**
     * 根据ID删除用户
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteByIds(Long... ids);
}
