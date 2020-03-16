package cn.dbdj1201.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author tyz1201
 * @datetime 2020-03-16 12:12
 **/
@SpringBootApplication
@EnableEurekaServer
public class ScRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScRegistryApplication.class, args);
    }
}
