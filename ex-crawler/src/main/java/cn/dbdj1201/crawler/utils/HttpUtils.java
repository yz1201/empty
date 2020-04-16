package cn.dbdj1201.crawler.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @author tyz1201
 * @datetime 2020-04-16 22:20
 **/
@Component
public class HttpUtils {
    private PoolingHttpClientConnectionManager pccm;

    public HttpUtils() {
        this.pccm = new PoolingHttpClientConnectionManager();
        //    设置最大连接数
        this.pccm.setMaxTotal(200);
        //    设置每个主机的并发数
        this.pccm.setDefaultMaxPerRoute(20);
    }

    //获取内容
    public String getHtml(String url) {
        // 获取HttpClient对象
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(this.pccm).build();
        // 声明httpGet请求对象
        HttpGet httpGet = new HttpGet(url);

        //設置httpGet的头部參數信息

        httpGet.setHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
//        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,sl;q=0.7");
//        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cookie", "xtest=3102.cf6b6759; __jdv=122270672|direct|-|none|-|1587048227879; __jdu=1587048227877782519080; shshshfpa=692ea620-4d71-fa1e-af4b-d02f8a311ad5-1587048228; areaId=7; ipLoc-djd=7-420-3127-0; pinId=YrVjvdodpCaG1DgEVoS7ULV9-x-f3wj7; pin=jd_7a16e96fa104e; unick=dbdj1201; _tp=oZGg9ccMmc6XXJJig%2FORVFlIdit4AI6dHosWIYfD2hY%3D; _pst=jd_7a16e96fa104e; shshshfpb=ofvNr1Si7Zm%2F6h4%2F%20k2n1eg%3D%3D; __jda=122270672.1587048227877782519080.1587048228.1587048228.1587048228.1; __jdc=122270672; shshshfp=32a1995e822ed7ca2a901ded46ea1992; qrsc=3; wlfstk_smdl=cdhf2gggi6a9f13ry5u661szp7r2lvdy; 3AB9D23F7A4B3C9B=GMBRH7AWHUYKY3WT5XZP4AKQBG52SPR4QVMVENLMIK7EOGRRWKZR2OS3E5JHQOXFZ7H7PLQ7EW6KTDAF2DAANBCJOE;" +
                " __jdb=122270672.16.1587048227877782519080|1.1587048228; shshshsID=13efe0f38218e7afdb05dc63b563bd28_10_1587050429275; rkv=V0000");
//        httpGet.setHeader("Host", "search.jd.com");
//        httpGet.setHeader("refer", "https://www.jd.com/");
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36");

        // 设置请求参数RequestConfig
        httpGet.setConfig(this.getConfig());
        // 使用HttpClient发起请求，返回response
        CloseableHttpResponse response = null;
        try {
            System.out.println(httpGet);
            response = client.execute(httpGet);
            // 解析response返回数据
            if (response.getStatusLine().getStatusCode() == 200) {
                // 如果response。getEntity获取的结果是空，在执行EntityUtils.toString会报错
                // 需要对Entity进行非空的判断
                if (response.getEntity() != null) {
                    return EntityUtils.toString(response.getEntity(), "utf8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 不能关闭，现在使用的是连接管理器
            // httpClient.close();
        }
        return null;
    }

    //获取图片
    public String getImage(String url) {
        // 获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.pccm).build();

        // 声明httpGet请求对象
        HttpGet httpGet = new HttpGet(url);
        // 设置请求参数RequestConfig
        httpGet.setConfig(this.getConfig());

        CloseableHttpResponse response = null;
        try {
            // 使用HttpClient发起请求，返回response
            response = httpClient.execute(httpGet);
            // 解析response下载图片
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取文件类型
                String extName = url.substring(url.lastIndexOf("."));
                // 使用uuid生成图片名
                String imageName = UUID.randomUUID().toString() + extName;

                // 声明输出的文件
                OutputStream outStream = new FileOutputStream(new File("F:\\temp\\crawler\\images\\" + imageName));
                // 使用响应体输出文件
                response.getEntity().writeTo(outStream);

                // 返回生成的图片名
                return imageName;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    // 关闭连接
                    response.close();
                }
                // 不能关闭，现在使用的是连接管理器
                // httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    //获取请求参数对象
    private RequestConfig getConfig() {

        return RequestConfig.custom().setConnectTimeout(1000)// 设置创建连接的超时时间
                .setConnectionRequestTimeout(500) // 设置获取连接的超时时间
                .setSocketTimeout(10000) // 设置连接的超时时间
                .build();
    }
}
