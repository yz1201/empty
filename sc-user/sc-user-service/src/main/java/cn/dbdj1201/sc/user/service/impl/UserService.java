package cn.dbdj1201.sc.user.service.impl;

import cn.dbdj120.sc.common.utils.CodecUtils;
import cn.dbdj120.sc.common.utils.NumberUtils;
import cn.dbdj1201.sc.user.mapper.UserMapper;
import cn.dbdj1201.sc.user.pojo.User;
import cn.dbdj1201.sc.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-04-02 1:12
 **/
@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    static final String KEY_PREFIX = "user:code:phone:";

    static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public Boolean checkUser(String data, Integer type) {
        User record = new User();
        if (type == 1)
            record.setUsername(data);
        else
            record.setPhone(data);
        return this.userMapper.selectCount(record) == 0;
    }

    @Override
    public Boolean sendVerifyCode(String phone) {
        // 生成验证码
        String code = NumberUtils.generateCode(6);
        try {
            // 发送短信
            Map<String, String> msg = new HashMap<>();
            msg.put("phone", phone);
            msg.put("code", code);
            logger.info("msg-> " + msg);
            this.amqpTemplate.convertAndSend("sc.sms.exchange", "sms.verify.code", msg);
            System.out.println("come here ? ");
            // 将code存入redis
            this.redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 5, TimeUnit.HOURS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送短信失败。phone：{}， code：{}", phone, code);
            return false;
        }
    }

    @Override
    public Boolean register(User user, String code) {
        String cacheCode = this.redisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());
        if (!StringUtils.equals(code, cacheCode)) {
            return false;
        }
        // 生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        // 对密码加密
        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));

        // 强制设置不能指定的参数为null
        user.setId(null);
        user.setCreated(new Date());

        // 添加到数据库
        boolean boo = this.userMapper.insertSelective(user) == 1;

        if (boo) {
            // 注册成功，删除redis中的记录
            this.redisTemplate.delete(KEY_PREFIX + user.getPhone());
        }

        System.out.println("boo - 》" + boo);
        return boo;
    }
}
