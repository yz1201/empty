package cn.dbdj1201.sc.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author tyz1201
 * @datetime 2020-03-25 17:20
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ScSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScSearchApplication.class);
    }
}
