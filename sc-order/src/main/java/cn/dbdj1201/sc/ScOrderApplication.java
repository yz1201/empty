package cn.dbdj1201.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author tyz1201
 * @datetime 2020-04-04 23:08
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("cn.dbdj1201.sc.order.mapper")
public class ScOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScOrderApplication.class, args);
    }
}
