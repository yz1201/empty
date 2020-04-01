package cn.dbdj1201.sc.user.controller;

import cn.dbdj1201.sc.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tyz1201
 * @datetime 2020-04-02 1:11
 **/
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户校验
     *
     * @param data 数据
     * @param type 数据类型 1，用户名 2，手机号码 默认值1
     * @return
     */
    @GetMapping("check/{data}/{type}")
    public ResponseEntity<Boolean> checkUser(@PathVariable(value = "data") String data,
                                             @PathVariable(value = "type", required = false) Integer type) {
        if (type == null)
            type = 1;
        Boolean boo = this.userService.checkUser(data, type);
        if (boo == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(boo);
    }
}
