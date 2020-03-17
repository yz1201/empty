package cn.dbdj1201.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author tyz1201
 * @datetime 2020-03-16 13:58
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.dbdj1201.sc.mapper")
public class ScItemServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScItemServiceApplication.class, args);
    }
}
