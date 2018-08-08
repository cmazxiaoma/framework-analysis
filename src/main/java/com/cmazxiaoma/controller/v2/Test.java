package com.cmazxiaoma.controller.v2;

import com.cmazxiaoma.controller.HttpUtil;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/7 16:31
 */
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            final int finalName = i;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpUtil.get("http://localhost:8081/test/4?name=" + finalName);
                }
            }).start();
        }
    }
}
