package cn.dbdj1201.goods.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author tyz1201
 * @datetime 2020-03-30 1:12
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ScGoodWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScGoodWebApplication.class);
    }
}
