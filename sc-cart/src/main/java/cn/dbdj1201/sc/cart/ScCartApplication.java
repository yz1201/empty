package cn.dbdj1201.sc.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author tyz1201
 * @datetime 2020-04-04 17:16
 **/
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ScCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScCartApplication.class, args);
    }
}
