package cn.dbdj1201.es;

import cn.dbdj1201.es.pojo.Item;
import cn.dbdj1201.es.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author tyz1201
 * @datetime 2020-03-23 11:08
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplication.class)
public class IndexTest {

    @Autowired
    private ElasticsearchTemplate template;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testIndex() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        this.template.createIndex(Item.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        this.template.putMapping(Item.class);
    }

    @Test
    public void testDelete() {
        this.template.deleteIndex("dbdj1201");

    }

    @Test
    public void testAddDoc() {
        Item item = new Item(1L, "小米手机7123412421", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        itemRepository.save(item);

        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);

        List<Item> list1 = new ArrayList<>();
        list1.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list1.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list1.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list1.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list1.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list1);
    }

    @Test
    public void testQueryIndex() {
//        this.itemRepository.findAll(Sort.by("price").descending()).forEach(System.out::println);
//        System.out.println(Objects.requireNonNull(this.itemRepository.findById(2L).get()));
        System.out.println("------------------------------");
        this.itemRepository.findByTitle("手机去哪里").forEach(System.out::println);
    }
}
