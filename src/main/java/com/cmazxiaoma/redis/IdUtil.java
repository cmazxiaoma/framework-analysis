package com.cmazxiaoma.redis;

import java.util.UUID;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/30 6:46
 */
public class IdUtil {

    public static String gen() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(IdUtil.gen());
    }
}
