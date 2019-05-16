package com.cmazxiaoma.controller.test;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/4/18 13:35
 */
public class ThreadLocalTest {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException, IllegalAccessException {
        for (int i = 0; i < 5; i++) {
            new Thread(new MyThreadLocalThread()).start();
        }
    }

    static class MyThreadLocalThread extends Thread {

        @Override
        public void run() {
            threadLocal.set(this.getName());
            System.out.println(threadLocal.get());
        }
    }
}
