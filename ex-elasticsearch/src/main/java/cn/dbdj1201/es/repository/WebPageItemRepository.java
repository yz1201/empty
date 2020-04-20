package cn.dbdj1201.es.repository;

import cn.dbdj1201.es.pojo.WebPageItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author tyz1201
 * @datetime 2020-04-20 11:24
 **/
public interface WebPageItemRepository extends ElasticsearchRepository<WebPageItem, Integer> {
}
