package cn.dbdj1201.sc.search.service;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.item.pojo.Spu;
import cn.dbdj1201.sc.search.pojo.Goods;
import cn.dbdj1201.sc.search.pojo.SearchRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author tyz1201
 * @datetime 2020-03-25 22:33
 **/
public interface ISearchService {

    Goods buildGoods(Spu spu) throws JsonProcessingException;

    PageResult<Goods> search(SearchRequest searchRequest);
}
