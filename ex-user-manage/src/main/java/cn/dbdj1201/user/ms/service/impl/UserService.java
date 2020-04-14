package cn.dbdj1201.user.ms.service.impl;

import cn.dbdj1201.user.ms.mapper.UserMapper;
import cn.dbdj1201.user.ms.pojo.User;
import cn.dbdj1201.user.ms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-14 15:57
 **/
@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void userRegister(User user) {

    }

    @Override
    public List<User> findAll() {
        return this.userMapper.selectAll();
    }

    @Override
    public User findUserById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void modifyUserInfo(User user) {
        this.userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Long... ids) {
//        Arrays.stream(ids).forEach(id -> this.userMapper.deleteByPrimaryKey(id));
        Arrays.stream(ids).forEach(this.userMapper::deleteByPrimaryKey);
    }
}
