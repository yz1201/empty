package cn.dbdj1201.sc.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author tyz1201
 * @datetime 2020-04-02 23:51
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ScAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScAuthApplication.class, args);
    }
}
