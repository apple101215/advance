package com.sundy.advance.homework.week2.作业6;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author sundy
 * @date 2021/7/4
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        HttpClientDemo.doGet("http://localhost:8801");
    }
    public static void doGet(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            System.out.println("响应内容:" + EntityUtils.toString(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
