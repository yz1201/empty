package cn.dbdj1201.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-16 10:31
 **/
public class Demo1Test {

    @Test
    public void iCrawler() throws IOException {
        //打开浏览器，创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //输入网址，发起get请求创建HttpGet对象
        HttpGet httpGet = new HttpGet("http://www.hupu.com");
        //按回车，发起请求，返回响应，使用HttpClient对象发起请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        //解析响应，获取数据
        //先判断状态码是否正常
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = httpResponse.getEntity();
            String content = EntityUtils.toString(httpEntity, "utf8");
            System.out.println(content);
        }
    }

    @Test
    public void crawler() throws IOException {
        //打开浏览器，创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //输入网址，发起get请求创建HttpGet对象
        HttpGet httpGet = new HttpGet("http://www.hupu.com");
        //按回车，发起请求，返回响应，使用HttpClient对象发起请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        //解析响应，获取数据
        //先判断状态码是否正常
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = httpResponse.getEntity();
            String content = EntityUtils.toString(httpEntity, "utf8");
            System.out.println(content);
        }
        httpResponse.close();
        httpClient.close();
    }

    /**
     * 带参数的get请求
     * https://my.hupu.com/search?fid=3441&type=undefined&q=uzi&sortBy=general
     * https://my.hupu.com/search?fid=3441&type=undefined&q=uzi&sortby=general
     */
    @Test
    public void getCrawlerWithParam() {
        //创建client
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet对象
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder("https://my.hupu.com/search");
            uriBuilder.setParameter("fid", "3441");
            uriBuilder.setParameter("type", "undefined");
            uriBuilder.setParameter("q", "uzi");
            uriBuilder.setParameter("sortBy", "general");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpGet httpGet;
        CloseableHttpResponse response = null;
        try {
            httpGet = new HttpGet(uriBuilder.build());
            System.out.println("发起请求的信息-》 " + httpGet);
            //发起请求
            response = httpClient.execute(httpGet);
            //解析响应,先判断响应码
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println(EntityUtils.toString(response.getEntity(), "utf8"));
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 不带参数的post请求
     */
    @Test
    public void postCrawler() {
        //创建client
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet对象
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder("https://my.hupu.com/search");
//            uriBuilder.setParameter("fid", "3441");
//            uriBuilder.setParameter("type", "undefined");
//            uriBuilder.setParameter("q", "uzi");
//            uriBuilder.setParameter("sortBy", "general");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpPost httpPost;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(uriBuilder.build());
            System.out.println("发起请求的信息-》 " + httpPost);
            //发起请求
            response = httpClient.execute(httpPost);
            //解析响应,先判断响应码
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println(EntityUtils.toString(response.getEntity(), "utf8"));
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 带参数的post请求
     */
    @Test
    public void postCrawlerWithParam() {
        //创建client
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet对象
//        URIBuilder uriBuilder = null;
//        try {
//            uriBuilder = new URIBuilder("https://my.hupu.com/search");
//            uriBuilder.setParameter("fid", "3441");
//            uriBuilder.setParameter("type", "undefined");
//            uriBuilder.setParameter("q", "uzi");
//            uriBuilder.setParameter("sortBy", "general");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }

        HttpPost httpPost;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost("https://my.hupu.com/search");
            //声明list集合，封装表单里的参数
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("fid", "3441"));
            params.add(new BasicNameValuePair("type", "undefined"));
            params.add(new BasicNameValuePair("q", "a"));
            params.add(new BasicNameValuePair("sortBy", "general"));

            //创建表单的Entity对象，第一个参数为封装好的表单数据，第二个参数是编码格式
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(params, "utf8");
            //设置表单的entity对象到post请求中
            httpPost.setEntity(encodedFormEntity);
            System.out.println("发起请求的信息-》 " + httpPost);
            //发起请求
            response = httpClient.execute(httpPost);
            //解析响应,先判断响应码
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println(EntityUtils.toString(response.getEntity(), "utf8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}