package cn.dbdj1201.sc.search.client;

import cn.dbdj1201.sc.item.api.CategoryApi;
import cn.dbdj1201.sc.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tyz1201
 * @datetime 2020-03-25 17:39
 **/
@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {
}
