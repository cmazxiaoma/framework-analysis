package com.cmazxiaoma.慕课网并发学习.安全发布对象;

import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/14 4:21
 */
public class Escape {

    private String name = null;

    public Escape() {
        new Thread(new MyThread()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {

        }
        name = "cmazxiaoma";
    }

    private class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println(Escape.this.name);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
