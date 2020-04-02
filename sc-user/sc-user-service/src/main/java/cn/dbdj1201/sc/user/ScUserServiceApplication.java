package cn.dbdj1201.sc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author tyz1201
 * @datetime 2020-04-02 0:49
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.dbdj1201.sc.user.mapper")
public class ScUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScUserServiceApplication.class, args);
    }
}
