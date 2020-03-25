package cn.dbdj1201.es;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * @author tyz1201
 * @datetime 2020-03-23 11:06
 **/
@SpringBootApplication
public class EsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class);
    }
//
//    @Bean
//    public RestHighLevelClient client() {
//
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("localhost:9200", "localhost:9201")
//                .build();
//
//        return RestClients.create(clientConfiguration).rest();
//    }

}
