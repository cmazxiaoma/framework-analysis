package com.cmazxiaoma.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/4 20:45
 */
public class ThreadTest {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println(Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Thread thread = threadFactory.newThread(Thread.currentThread());
        System.out.println(thread.getId() + ":" + thread.getName());
    }

}
