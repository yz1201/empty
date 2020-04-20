package cn.dbdj1201.es;

import cn.dbdj1201.es.pojo.Item;
import cn.dbdj1201.es.pojo.WebPageItem;
import cn.dbdj1201.es.repository.WebPageItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tyz1201
 * @datetime 2020-04-20 11:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplication.class)
public class IndexTest2 {
    @Autowired
    private ElasticsearchTemplate template;
    @Autowired
    private WebPageItemRepository itemRepository;

    @Test
    public void testIndex() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        this.template.createIndex(WebPageItem.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        this.template.putMapping(Item.class);
    }
}
