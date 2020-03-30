package cn.dbdj1201.goods.web.service;

import java.util.Map;

/**
 * @author tyz1201
 * @datetime 2020-03-30 16:29
 **/
public interface IGoodsService {
    Map<String, Object> loadData(Long spuId);
}
