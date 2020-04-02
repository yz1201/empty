package cn.dbdj1201.sc.user.api;

import cn.dbdj1201.sc.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tyz1201
 * @datetime 2020-04-03 0:49
 **/
@RequestMapping("user")
public interface UserApi {

    @GetMapping("query")
    User queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password);
}