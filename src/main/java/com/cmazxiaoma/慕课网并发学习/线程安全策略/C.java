package com.cmazxiaoma.慕课网并发学习.线程安全策略;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/14 5:02
 */
public class C {

    public static void main(String[] args) {
        Map<String, String> hashMap = Maps.newHashMap();
        hashMap.put("boy", "cmazxiaoma");

        Map<String, String> newHashMap = Collections.unmodifiableMap(hashMap);
        newHashMap.put("girl", "doudou");
    }
}
