package cn.dbdj1201.sc.user.service.impl;

import cn.dbdj1201.sc.user.mapper.UserMapper;
import cn.dbdj1201.sc.user.pojo.User;
import cn.dbdj1201.sc.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tyz1201
 * @datetime 2020-04-02 1:12
 **/
@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Boolean checkUser(String data, Integer type) {
        User record = new User();
        if (type == 1)
            record.setUsername(data);
        else
            record.setPhone(data);
        return this.userMapper.selectCount(record) == 0;
    }
}
