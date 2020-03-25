package cn.dbdj1201.sc.search.repository;

import cn.dbdj1201.sc.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author tyz1201
 * @datetime 2020-03-26 1:00
 **/
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
