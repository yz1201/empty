package cn.dbdj1201.crawler;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author tyz1201
 * @datetime 2020-04-16 17:00
 * jsoup dom api -st
 **/
public class JsoupDemo1 {

    /**
     * 从dom中获取元素
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String string = FileUtils.readFileToString(new File("F:\\maven_project\\empty\\ex-crawler\\src\\main\\resources\\test.html"),
                "utf8");
        Document document = Jsoup.parse(string);
        System.out.println(document.getElementById("pppp").text());
        System.out.println(document.getElementsByTag("button").first().text());
        System.out.println(document.getElementsByClass("class1 class2").first().text());
        System.out.println(document.getElementsByAttribute("abc").first().text());
        System.out.println(document.getElementsByAttributeValue("href", "javascript:void(0)").first().text());
        System.out.println(document.getElementsByAttributeValue("href", "https://www.baidu.com").first().text());
    }

    /**
     * 从元素中获取数据
     */
    @Test
    public void test1() throws IOException {
        String string = FileUtils.readFileToString(new File("F:\\maven_project\\empty\\ex-crawler\\src\\main\\resources\\test.html"),
                "utf8");
        Document document = Jsoup.parse(string);
        Elements a = document.getElementsByTag("a");

        System.out.println(a.first().id());
        System.out.println(a.first().classNames());
        System.out.println(a.first().attributes());
        System.out.println(a.first().attr("test1"));
        System.out.println(a.first().text());
    }

    /**
     * 选择器查询元素，跟js中一致
     *
     * @throws IOException
     */
    @Test
    public void selectorTest() throws IOException {
        String string = FileUtils.readFileToString(new File("F:\\maven_project\\empty\\ex-crawler\\src\\main\\resources\\test.html"),
                "utf8");
        Document document = Jsoup.parse(string);
        Elements elements = document.select("a");
        elements.forEach(System.out::println);

        Elements elements1 = document.select("#pppp");
        elements1.forEach(System.out::println);
        System.out.println(elements1.first().text());

        System.out.println(document.select(".test1").first().text());

        System.out.println(document.select("[abc]").first().text());

        document.select("[class=test1 test2 test3]").forEach(System.out::println);
    }

    /**
     * 组合选择器
     */
    @Test
    public void selectorTest2() throws IOException {
        String string = FileUtils.readFileToString(new File("F:\\maven_project\\empty\\ex-crawler\\src\\main\\resources\\test.html"),
                "utf8");
        Document document = Jsoup.parse(string);

        System.out.println(document.select("a#a1").first().text());
        System.out.println(document.select("a.test1").first().text());

        document.select("a[class=test1 test2 test3].test2.test3#a1").forEach(System.out::println);

        System.out.println("------>");
        document.select("#app01 a").forEach(System.out::println); //后代a

        System.out.println("------>");
        document.select("#son1 > *").forEach(System.out::println); //直接子元素a
    }
}
