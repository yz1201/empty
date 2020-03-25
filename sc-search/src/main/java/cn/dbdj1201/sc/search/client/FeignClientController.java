package cn.dbdj1201.sc.search.client;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author tyz1201
 * @datetime 2020-03-25 23:23
 **/
//@Component
@Import(FeignClientsConfiguration.class)
public class FeignClientController {
    private GoodsClient goodsClient;
    private CategoryClient categoryClient;
    private BrandClient brandClient;
    private SpecificationClient specificationClient;


    public FeignClientController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
        this.goodsClient = Feign.builder().client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                //默认是Logger.NoOpLogger
                .logger(new Slf4jLogger(GoodsClient.class))
                //默认是Logger.Level.NONE
                .logLevel(Logger.Level.FULL)
                .target(GoodsClient.class, "http://api.sc1.com/api/item-service");

        this.categoryClient = Feign.builder().client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                //默认是Logger.NoOpLogger
                .logger(new Slf4jLogger(CategoryClient.class))
                //默认是Logger.Level.NONE
                .logLevel(Logger.Level.FULL)
                .target(CategoryClient.class, "http://api.sc1.com/api/item-service");

        this.brandClient = Feign.builder().client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                //默认是Logger.NoOpLogger
                .logger(new Slf4jLogger(BrandClient.class))
                //默认是Logger.Level.NONE
                .logLevel(Logger.Level.FULL)
                .target(BrandClient.class, "http://api.sc1.com/api/item-service");

        this.specificationClient = Feign.builder().client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                //默认是Logger.NoOpLogger
                .logger(new Slf4jLogger(SpecificationClient.class))
                //默认是Logger.Level.NONE
                .logLevel(Logger.Level.FULL)
                .target(SpecificationClient.class, "http://api.sc1.com/api/item-service");
    }
}
