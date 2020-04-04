package cn.dbdj1201.sc.order.service.api;

import cn.dbdj1201.sc.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "sc-gateway", path = "/api/item")
public interface GoodsService extends GoodsApi {
}
