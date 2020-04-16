package cn.dbdj1201.crawler;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author tyz1201
 * @datetime 2020-04-16 11:29
 * HttpClientPool测试使用
 **/
public class HttpClientPoolTest {

    @Test
    public void testPool() throws IOException {
        //创建连接池管理器
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        manager.setMaxTotal(100);
        //设置每个主机的最大连接数
        manager.setDefaultMaxPerRoute(10);
        //使用连接池管理器发起请求
        doGet(manager);
//        doPost(manager);
    }

    private void doPost(PoolingHttpClientConnectionManager manager) {

    }

    private void doGet(PoolingHttpClientConnectionManager manager) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000)      //创建连接的最长时间
                .setConnectionRequestTimeout(500)           //设置获取连接的最长时间
                .setSocketTimeout(10 * 1000).build();//数据传输的最长时间
        HttpGet httpGet = new HttpGet("https://baidu.com");
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = HttpClients.custom().setConnectionManager(manager).build()
                .execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200)
            System.out.println(EntityUtils.toString(response.getEntity(), "utf8"));
    }
}
