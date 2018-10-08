package com.cmazxiaoma;

import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/27 17:52
 */
public class Test {


    public static void main(String[] args) {
        test2();
    }


    public static void test1() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        System.out.println("hello");
    }

    public static void test2() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception ex) {

        }
        System.out.println("hello");
    }
}
