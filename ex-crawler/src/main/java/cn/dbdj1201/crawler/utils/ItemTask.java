package cn.dbdj1201.crawler.utils;

import cn.dbdj1201.crawler.pojo.Item;
import cn.dbdj1201.crawler.service.IItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-16 22:36
 **/
@Component
public class ItemTask {
    @Autowired
    private HttpUtils httpUtils;
    @Autowired
    private IItemService itemService;

    public static final ObjectMapper MAPPER = new ObjectMapper();

    //设置定时任务执行完成后，再间隔100秒执行一次
    @Scheduled(fixedDelay = 1000 * 100)
    public void process() throws Exception {
        //分析页面发现访问的地址,页码page从1开始，下一页page加2
        //https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&suggest=1.his.0.0&wq=&pvid=f40f472ab05f4015bd089806f05ed34b
//        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8" +
//                "&qrst=1&rt=1%22%20+%20%22&stop=1&vt=2&cid2=653&cid3=655&s=5760&click=0&page=";

        String url ="https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&suggest=1.his.0.0&wq=&pvid=f40f472ab05f4015bd089806f05ed34b&page=";
        //遍历执行，获取所有的数据
        for (int i = 1; i < 10; i = i + 2) {
            //发起请求进行访问，获取页面数据,先访问第一页
            String html = this.httpUtils.getHtml(url + i);
            //解析页面数据，保存数据到数据库中
            this.parseHtml(html);
        }
        System.out.println("执行完成");
    }

    //解析页面，并把数据保存到数据库中
    private void parseHtml(String html) throws Exception {
        //使用jsoup解析页面
        Document document = Jsoup.parse(html);

        //获取商品数据
        Elements spus = document.select("div#J_goodsList > ul > li");

        System.out.println("spus -> " + spus.first().text());

        //遍历商品spu数据
        for (Element spuEle : spus) {
            //获取商品spu
            Long spuId = Long.parseLong(spuEle.attr("data-spu"));

            //获取商品sku数据
            Elements skus = spuEle.select("li.ps-item img");
            for (Element skuEle : skus) {
                //获取商品sku
                Long skuId = Long.parseLong(skuEle.attr("data-sku"));

                //判断商品是否被抓取过，可以根据sku判断
                Item param = new Item();
                param.setSku(skuId);
                List<Item> list = this.itemService.findAll(param);
                //判断是否查询到结果
                if (list.size() > 0) {
                    //如果有结果，表示商品已下载，进行下一次遍历
                    continue;
                }

                //保存商品数据，声明商品对象
                Item item = new Item();

                //商品spu
                item.setSpu(spuId);
                //商品sku
                item.setSku(skuId);
                //商品url地址
                item.setUrl("https://item.jd.com/" + skuId + ".html");
                //创建时间
                item.setCreated(new Date());
                //修改时间
                item.setUpdated(item.getCreated());


                //获取商品标题
                String itemHtml = this.httpUtils.getHtml(item.getUrl());
                String title = Jsoup.parse(itemHtml).select("div.sku-name").text();
                item.setTitle(title);

                //获取商品价格
                String priceUrl = "https://p.3.cn/prices/mgets?skuIds=J_" + skuId;
                String priceJson = this.httpUtils.getHtml(priceUrl);
                //解析json数据获取商品价格
                double price = MAPPER.readTree(priceJson).get(0).get("p").asDouble();
                item.setPrice(price);

                //获取图片地址
                String pic = "https:" + skuEle.attr("data-lazy-img").replace("/n9/", "/n1/");
                System.out.println(pic);
                //下载图片
                String picName = this.httpUtils.getImage(pic);
                item.setPic(picName);

                System.out.println("i save the item -> " + item);
                //保存商品数据
                this.itemService.save(item);
            }
        }
    }

}
