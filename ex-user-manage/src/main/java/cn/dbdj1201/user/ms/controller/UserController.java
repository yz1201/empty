package cn.dbdj1201.user.ms.controller;

import cn.dbdj1201.user.ms.pojo.User;
import cn.dbdj1201.user.ms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-14 16:13
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> listAll() {
        List<User> users = this.userService.findAll();
        if (CollectionUtils.isEmpty(users))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(users);
    }
}
