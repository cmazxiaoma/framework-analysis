package com.cmazxiaoma.quzhuanxiang;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/9/26 18:07
 */
public class Test3 {

    public static void main(String[] args) {
        Map<Integer, Integer> resultMap = Maps.newHashMapWithExpectedSize(6);
        for (int i = 0; i < 1000; i++) {
            resultMap.put(i, i);
        }
        System.out.println(resultMap);

        Long a = 666L;
        Long b = 66L;
        System.out.println(a.compareTo(b) >= 0L);
    }
}
