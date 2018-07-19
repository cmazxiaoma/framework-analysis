package com.cmazxiaoma.lru;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/7/19 16:41
 */
public class LruTest {

    @Test
    public void lru() {
        int size = 3;

        /**
         * false, 基于插入排序
         * true, 基于访问排序
         */
        Map<String, String> map = new LinkedHashMap<String, String>(size, .75F,
                true) {

            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                boolean tooBig = size() > size;

                if (tooBig) {
                    System.out.println("最近最少使用的key=" + eldest.getKey());
                }
                return tooBig;
            }
        };

        map.put("1", "百度");
        map.put("2", "阿里巴巴");
        map.put("3", "腾讯");


        // 开始使用key=1&value=百度
        map.get("1");

        // 开始使用key=2&value=阿里巴巴
        map.get("2");

        // 插入数据
        map.put("4", "美团");

        // 被移除的应该是key=3&value=腾讯
        System.out.println("map=" + map.toString());
    }
}
