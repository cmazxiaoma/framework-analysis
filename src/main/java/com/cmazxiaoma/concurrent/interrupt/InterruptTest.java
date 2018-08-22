package com.cmazxiaoma.concurrent.interrupt;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/21 11:38
 */
public class InterruptTest {

    public static void main(String[] args) {
        Thread wt = Thread.currentThread();

        System.out.println(wt.isInterrupted());
        System.out.println(wt.isInterrupted());

        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
    }
}
