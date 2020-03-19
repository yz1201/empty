package cn.dbdj1201.sc.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tyz1201
 * @datetime 2020-03-18 22:12
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ScUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScUploadApplication.class);
    }
}
