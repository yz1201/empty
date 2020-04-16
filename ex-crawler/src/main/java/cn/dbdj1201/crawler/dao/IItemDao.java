package cn.dbdj1201.crawler.dao;

import cn.dbdj1201.crawler.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tyz1201
 * @datetime 2020-04-16 22:03
 **/
public interface IItemDao extends JpaRepository<Item,Long> {
}
