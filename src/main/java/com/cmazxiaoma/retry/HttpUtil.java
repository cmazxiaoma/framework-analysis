package com.cmazxiaoma.retry;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * http请求工具
 *
 * @author Ale （2018.1.30修改 底层换用okhttp实现）
 * @createTime 2015年2月28日 上午11:02:42
 */
@Slf4j
public class HttpUtil {

    private static final Integer CONNECTION_TIMEOUT = 5 * 1000;
    private static final Integer SO_TIMEOUT = 60 * 1000;
    private final static Long CONN_MANAGER_TIMEOUT = 500L;

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static OkHttpClient mHttpClient;

    static {
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)       //设置连接超时
                .readTimeout(60, TimeUnit.SECONDS)          //设置读超时
                .writeTimeout(60, TimeUnit.SECONDS)          //设置写超时
                .retryOnConnectionFailure(true)             //是否自动重连
                .build();                                   //构建OkHttpClient对象
    }


    public static String doGet(String url, String charSet, Map<String, Object> headers) {
        return doGet(url, charSet, headers, null);
    }

    public static String doGet(String url, String charSet, Map<String, Object> headers, Map<String, Object> params) {
        logger.debug("http get visit url:" + url);

        String resContent = null;
        try {
            //构建URL参数
            if (params != null) {
                for (String key : params.keySet()) {
                    if (url.contains("?"))
                        url += "&" + key + "=" + params.get(key);
                    else
                        url += "?" + key + "=" + params.get(key);

                }
            }
            //OkHttpClient mHttpClient = new OkHttpClient.Builder().build();
            Request request = BulidRequest(url, headers);
            Response response = mHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful())
                return response.body().string();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return resContent;
    }


    public static String doPut(String url, String charSet, Map<String, Object> map, Map<String, Object> headers) {
        logger.debug("http put visit url:" + url + ", params:" + map);
        try {
            // OkHttpClient mHttpClient = new OkHttpClient.Builder().build();
            Request request = BuildPutRequesr(url, map, headers);
            Response response = mHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                String out = response.body().string();
                return out;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "error";
    }

    public static String doPost(String url, String charSet, Map<String, Object> map, Map<String, Object> headers) {
        logger.debug("http post visit url:" + url + ", params:" + map);
        try {
            // OkHttpClient mHttpClient = new OkHttpClient.Builder().build();
            Request request = BulidPostRequest(url, map, headers);
            Response response = mHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                String out = response.body().string();
                return out;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "error";
    }

    public static String doPostJson(String url, String json) {

        //OkHttpClient mHttpClient = new OkHttpClient.Builder().build();
        try {
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
            Request request = new Request.Builder().url(url).post(requestBody).build();
            Response response = mHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                String out = response.body().string();
                logger.info("http post rlt:" + out);
                return out;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "error";
    }

    public static String doPostJson(String url, String charSet, Map<String, Object> map) {
        logger.debug("http post json visit url:" + url + ", params:" + map);

        //OkHttpClient mHttpClient = new OkHttpClient.Builder().build();
        try {

            String json = JSON.toJSONString(map);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
            Request request = new Request.Builder().url(url).post(requestBody).build();
            Response response = mHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                String out = response.body().string();
                logger.info("http post rlt:" + out);
                return out;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "error";
    }

    public static String doPostJson(String url, String charSet, JsonObject data) {

        OkHttpClient mHttpClient = new OkHttpClient.Builder().build();
        try {
            String json = JSON.toJSONString(data);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
            Request request = new Request.Builder().url(url).post(requestBody).build();
            Response response = mHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                String out = response.body().string();
                logger.info("http post rlt:" + out);
                return out;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "error";
    }


    /**
     * 　　* @Description: 构建Request请求地址
     * 　　* @param  * @param url
     *
     * @param headers 　　* @return ${return_type}
     *                　　* @throws
     */
    private static Request BulidRequest(String url, Map<String, Object> headers) {

        Request.Builder requestBulid = new Request.Builder().url(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                requestBulid.addHeader(key, headers.get(key).toString());
            }
        }
        Request request = requestBulid.build();
        return request;
    }

    /**
     * 　　* @Description: 构建Request请求地址
     * 　　* @param  url 请求参数
     *
     * @param headers 　　* @return ${return_type}
     *                　　* @throws
     */
    private static Request BulidPostRequest(String url, Map<String, Object> map, Map<String, Object> headers) {

        Request.Builder requestBulid = new Request.Builder().url(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                requestBulid.addHeader(key, (String) headers.get(key));
            }
        }
        FormBody.Builder formBodyBulider = new FormBody.Builder();
        if (map != null) {
            for (String key : map.keySet()) {
                if (map.get(key) != null) {
                    formBodyBulider.add(key, map.get(key) + "");
                }
            }
        }
        Request request = requestBulid.post(formBodyBulider.build()).build();
        return request;
    }



    /**
     * 　　* @Description: 构建Request请求地址
     * 　　* @param  url 请求参数
     *
     * @param headers 　　* @return ${return_type}
     *                　　* @throws
     */
    private static Request BuildPutRequesr(String url, Map<String, Object> map, Map<String, Object> headers) {
        Request.Builder requestBulid = new Request.Builder().url(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                requestBulid.addHeader(key, headers.get(key).toString());
            }
        }
//        FormBody.Builder formBodyBulider = new FormBody.Builder();
//        if (map != null) {
//            for (String key : map.keySet()) {
//                if (map.get(key) != null) {
//                    formBodyBulider.add(key, map.get(key) + "");
//                }
//            }
//        }
//        Request request = requestBulid.put(formBodyBulider.build()).build();
        RequestBody requestBody = FormBody.create(MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE), JSON.toJSONString(map));
        Request request = requestBulid.url(url).put(requestBody).build();

        return request;
    }



    public static void main(String[] args) {

        System.out.println(doGet("http://www.163.com", "utf-8", null));
    }


}
