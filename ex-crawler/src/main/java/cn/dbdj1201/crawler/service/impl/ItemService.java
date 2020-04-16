package cn.dbdj1201.crawler.service.impl;

import cn.dbdj1201.crawler.dao.IItemDao;
import cn.dbdj1201.crawler.pojo.Item;
import cn.dbdj1201.crawler.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-16 22:04
 **/
@Service
public class ItemService implements IItemService {
    @Autowired
    private IItemDao itemDao;

    @Override
    public List<Item> findAll(Item item) {
        Example<Item> example = Example.of(item);
        return this.itemDao.findAll(example);
    }

    @Override
    @Transactional
    public void save(Item item) {
        this.itemDao.save(item);
    }
}
