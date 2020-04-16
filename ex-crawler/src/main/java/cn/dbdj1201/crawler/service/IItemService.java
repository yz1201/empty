package cn.dbdj1201.crawler.service;

import cn.dbdj1201.crawler.pojo.Item;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-16 22:04
 **/
public interface IItemService {
    //根据条件查询数据
    public List<Item> findAll(Item item);

    //保存数据
    public void save(Item item);
}
