package com.cmazxiaoma.coupon;

import com.alibaba.fastjson.JSON;
import com.google.gson.reflect.TypeToken;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/11/8 20:14
 */
public class Test {

    public static void main(String[] args) {
        String str = "[\n" +
                "    {\n" +
                "        \"couponId\":\"1\",\n" +
                "        \"couponExpireStartTime\":\"2019-11-08 00:00:00\",\n" +
                "        \"couponExpireEndTime\":\"2019-11-08 00:00:00\",\n" +
                "        \"couponRedirectType\":\"0\",\n" +
                "        \"couponRedirectUrl\":\"http://jenkins.yingzhongshare.com:8899/jenkins/view/%E8%B6%A3%E4%B8%93%E4%BA%AB/\",\n" +
                "        \"couponRedirectSourceId\":\"123456\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"couponId\":\"1\",\n" +
                "        \"couponExpireStartTime\":\"2019-11-08 00:00:00\",\n" +
                "        \"couponExpireEndTime\":\"2019-11-08 00:00:00\",\n" +
                "        \"couponRedirectType\":\"1\",\n" +
                "        \"couponRedirectUrl\":\"http://jenkins.yingzhongshare.com:8899/jenkins/view/%E8%B6%A3%E4%B8%93%E4%BA%AB/\",\n" +
                "        \"couponRedirectSourceId\":\"123456\"\n" +
                "    }\n" +
                "]";

        List<CouponTextVo> couponTextVoList =
                JSON.parseObject(str, new TypeToken<List<CouponTextVo>>(){}.getType());
        System.out.println(couponTextVoList.toString());

        System.out.println(JSON.toJSONString(couponTextVoList));

        String str1 = "{\n" +
                "    \"91000\":\"2019-11-17 15:00:00\",\n" +
                "    \"91000\":\"2019-11-17 15:00:00\",\n" +
                "    \"91000\":\"2019-11-17 15:00:00\",\n" +
                "    \"91000\":\"2019-11-17 15:00:00\",\n" +
                "    \"91000\":\"2019-11-17 15:00:00\"\n" +
                "}";
        Map<String, String> channelMap = JSON.parseObject(str1, Map.class);
        System.out.println(channelMap);

        System.out.println(channelMap.get(91000 + ""));

        System.out.println(channelMap.keySet());

        Boolean str2 = true;
        System.out.println(ObjectSizeCalculator.getObjectSize(str2));

        for (int i = 0; i < 1000; i++) {
            System.out.println(ThreadLocalRandom.current().nextInt(0, 1));
        }

        System.out.println(Objects.equals(null, "123"));
    }
}
