package cn.dbdj1201.sc.search.service.impl;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.item.bo.SpuBo;
import cn.dbdj1201.sc.search.client.GoodsClient;
import cn.dbdj1201.sc.search.pojo.Goods;
import cn.dbdj1201.sc.search.repository.GoodsRepository;
import cn.dbdj1201.sc.search.service.IIndexService;
import cn.dbdj1201.sc.search.service.ISearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tyz1201
 * @datetime 2020-03-26 1:14
 **/
@Service
public class IndexService implements IIndexService {
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private ISearchService searchService;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private ElasticsearchTemplate template;

    @Override
    public void saveGoods() {
        this.template.createIndex(Goods.class);
        this.template.putMapping(Goods.class);
        Integer page = 1;
        int rows = 100;
        do {
            PageResult<SpuBo> result = this.goodsClient.querySpuByPage(null, true, page, rows);
            List<SpuBo> spuBos = result.getItems();
            List<Goods> goods = spuBos.stream().map(spuBo -> {
                try {
                    return searchService.buildGoods(spuBo);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            goodsRepository.saveAll(goods);
            rows = spuBos.size();
            page++;
        } while (rows == 100);

    }
}
