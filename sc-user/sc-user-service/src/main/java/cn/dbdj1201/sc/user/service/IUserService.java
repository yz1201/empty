package cn.dbdj1201.sc.user.service;

import cn.dbdj1201.sc.user.pojo.User;

/**
 * @author tyz1201
 * @datetime 2020-04-02 1:11
 **/
public interface IUserService {
    /**
     * 用户名或者电话号码校验
     * @param data
     * @param type
     * @return
     */
    Boolean checkUser(String data, Integer type);

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    Boolean sendVerifyCode(String phone);

    /**
     * 用户注册
     * @param user
     * @param code
     * @return
     */
    Boolean register(User user, String code);

    User queryByUsernameAndPassword(String username, String password);
}
