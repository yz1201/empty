package cn.dbdj1201.sc.cart.client;

import cn.dbdj1201.sc.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tyz1201
 * @datetime 2020-04-04 21:50
 **/
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
