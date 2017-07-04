package org.dolphin.commons.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
/**
 * httpclient4.5连接池配置
 * @author yaokai
 *
 */
public class ClientConfiguration {

    public final static void main(String[] args) throws Exception {
        // Create a connection manager with custom configuration.
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        // Configure total max or per route limits for persistent connections
        // that can be kept in the pool or leased by the connection manager.
        connManager.setMaxTotal(100);//整个连接池最大
        connManager.setDefaultMaxPerRoute(10);//每个主机并发数

        RequestConfig defaultRequestConfig = RequestConfig.custom().build();

        // Create an HttpClient with the given custom dependencies and configuration.
        CloseableHttpClient httpclient = HttpClients.custom()
            .setConnectionManager(connManager)
            .setDefaultRequestConfig(defaultRequestConfig)
            .build();

        try {
            HttpGet httpget = new HttpGet("http://www.baidu.com");
//        	StringEntity stringEntity = new StringEntity("post request json string","UTF-8");
            // Request configuration can be overridden at the request level.
            // They will take precedence over the one set at the client level.
            RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
                .setConnectionRequestTimeout(5000)//从连接池中获取连接超时时间
                .setConnectTimeout(20*1000)//创建连接超时时间
                .setSocketTimeout(5000)//等待数据超时时间
                .build();
            httpget.setConfig(requestConfig);
            System.out.println("executing request " + httpget.getURI());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println(EntityUtils.toString(response.getEntity(),"UTF-8"));
                System.out.println("----------------------------------------");
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}