package cn.dbdj1201.sc.auth.service.impl;

import cn.dbdj1201.sc.auth.client.UserClient;
import cn.dbdj1201.sc.auth.config.JwtProperties;
import cn.dbdj1201.sc.auth.entity.UserInfo;
import cn.dbdj1201.sc.auth.service.IAuthService;
import cn.dbdj1201.sc.auth.utils.JwtUtils;
import cn.dbdj1201.sc.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tyz1201
 * @datetime 2020-04-03 0:29
 **/
@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties properties;

    @Override
    public String authentication(String username, String password) {
        try {
            // 调用微服务，执行查询
            User user = this.userClient.queryUser(username, password);
            // 如果查询结果为null，则直接返回null
            if (user == null) {
                return null;
            }
            // 如果有查询结果，则生成token
            return JwtUtils.generateToken(new UserInfo(user.getId(), user.getUsername()),
                    properties.getPrivateKey(), properties.getExpire());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
