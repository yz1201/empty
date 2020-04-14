package cn.dbdj1201.user.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author tyz1201
 * @datetime 2020-04-14 15:33
 **/
@SpringBootApplication
@MapperScan("cn.dbdj1201.user.ms.mapper")
public class UserMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserMSApplication.class, args);
    }
}
