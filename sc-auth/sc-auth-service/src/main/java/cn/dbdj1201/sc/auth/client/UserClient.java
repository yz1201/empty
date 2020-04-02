package cn.dbdj1201.sc.auth.client;

import cn.dbdj1201.sc.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tyz1201
 * @datetime 2020-04-03 0:50
 **/
@FeignClient("user-service")
public interface UserClient extends UserApi {
}
