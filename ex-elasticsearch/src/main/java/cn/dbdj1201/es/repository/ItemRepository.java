package cn.dbdj1201.es.repository;

import cn.dbdj1201.es.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-23 11:42
 **/
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

    List<Item> findByTitle(String title);
}
