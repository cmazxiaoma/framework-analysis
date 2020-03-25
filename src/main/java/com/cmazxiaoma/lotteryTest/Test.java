package com.cmazxiaoma.lotteryTest;

import java.text.MessageFormat;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/10/24 15:57
 */
public class Test {

    public static void main(String[] args) {
        String token5 = "{\n" +
                "    \"shandle\":\"0\",\n" +
                "    \"data\":{\n" +
                "        \"shandle\":1,\n" +
                "        \"phead\":{\n" +
                "            \"pversion\":82,\n" +
                "            \"phoneid\":\"dc03debda990e301\",\n" +
                "            \"phone\":\"MI 8\",\n" +
                "            \"imei\":\"860758047970837\",\n" +
                "            \"cversion\":123,\n" +
                "            \"cversionname\":\"2.16.0\",\n" +
                "            \"channel\":\"91000\",\n" +
                "            \"lang\":\"zh_cn\",\n" +
                "            \"sdk\":28,\n" +
                "            \"imsi\":\"46002\",\n" +
                "            \"sys\":\"9\",\n" +
                "            \"lng\":-1,\n" +
                "            \"lat\":-1,\n" +
                "            \"cityid\":-1,\n" +
                "            \"gcityid\":-1,\n" +
                "            \"platform\":\"android\",\n" +
                "            \"prdid\":\"9000\",\n" +
                "            \"time_zone\":\"GMT+08:00\",\n" +
                "            \"timezoneid\":\"Asia/Shanghai\",\n" +
                "            \"dpi\":\"1080*2028\",\n" +
                "            \"user_create_time\":\"2019-04-28 12:52:45\",\n" +
                "            \"phone_number\":\"15827557996\",\n" +
                "            \"access_token\":\"TOKEN_TEMPLATE\",\n" +
                "            \"net\":\"WIFI\",\n" +
                "            \"mac\":\"48:2C:A0:63:64:FE\",\n" +
                "            \"shumei_deviceid\":\"20190427133939788a78fe6509fb131c3c8d48d8027285010f1eabfc5d7c4c\",\n" +
                "            \"vendor\":\"Xiaomi\",\n" +
                "            \"ua\":\"Mozilla/5.0 (Linux; Android 9; MI 8 Build/PKQ1.180729.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/71.0.3578.99 Mobile Safari/537.36\"\n" +
                "        },\n" +
                "        \"customDate\":\"2019-10-25 12:00:00\"\n" +
                "    },\n" +
                "    \"handle\":0\n" +
                "}";

        System.out.println(token5.replace("TOKEN_TEMPLATE", "123"));
    }
}
