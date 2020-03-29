package cn.dbdj1201.goods.web.controller;

import cn.dbdj1201.goods.web.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-30 1:25
 **/
@RequestMapping("/hello")
@Controller
public class HelloController {

    @GetMapping("test")
    public String test(Model model) {
        model.addAttribute("msg", "Hello Thymeleaf>>??");
        return "hello";
    }

    @GetMapping("test2")
    public String test2(Model model) {
        User user = new User();
        user.setAge(21);
        user.setName("JackChen");
        user.setFriend(new User("李小龙", 30));
        model.addAttribute("msg", "Hello Thymeleaf>>??");
        model.addAttribute("user", user);
        model.addAttribute("date", new Date());

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user);
        list.add(user);
        list.add(user);
        model.addAttribute("list", list);
        return "hello";
    }
}
