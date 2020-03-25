package cn.dbdj1201.sc.search.client;

import cn.dbdj1201.sc.search.ScSearchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-25 22:49
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScSearchApplication.class)
public class GoodsClientTest {

    @Autowired
    private GoodsClient goodsClient;
//    @Autowired
//    private CategoryClient categoryClient;

    @Test
    public void testQuery() {
        System.out.println(goodsClient.querySpuDetailBySpuId(2L));
//        List<Long> ids = new ArrayList<>();
//        ids.add(74L);
//        ids.add(75L);
//        ids.add(76L);
//        this.categoryClient.queryNamesByIds(ids).forEach(System.out::println);
    }

}