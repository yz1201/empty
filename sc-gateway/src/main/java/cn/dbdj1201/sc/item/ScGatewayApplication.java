package cn.dbdj1201.sc.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author tyz1201
 * @datetime 2020-03-16 12:18
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ScGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScGatewayApplication.class, args);
    }
}
