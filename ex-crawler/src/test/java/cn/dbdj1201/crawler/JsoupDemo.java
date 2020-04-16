package cn.dbdj1201.crawler;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author tyz1201
 * @datetime 2020-04-16 16:47
 * jsoup使用
 **/
public class JsoupDemo {

    /**
     * jsoup解析url地址
     *
     * @throws IOException
     */
    @Test
    public void jsoupTest() throws IOException {
        //解析url地址
        Document doc = Jsoup.parse(new URL("http://www.hupu.com"), 2000);
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    /**
     * jsoup解析html文件/字符串
     *
     * @throws IOException
     */
    @Test
    public void stringTest() throws IOException {
        String string = FileUtils.readFileToString(new File("F:\\maven_project\\empty\\ex-crawler\\src\\main\\resources\\test.html"),
                "utf8");
        Document document = Jsoup.parse(string);
        System.out.println(document.getElementsByTag("title").first().text());

        System.out.println(Jsoup.parse(new File("F:\\maven_project\\empty\\ex-crawler\\src\\main\\resources\\test.html"),
                "utf8").getElementsByTag("title").first().text());
    }
}
