package com.cmazxiaoma.慕课网并发学习.JUC;

import org.springframework.util.ReflectionUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.util.concurrent.locks.LockSupport;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/15 22:34
 */
public class UnsafeParkTest {

    public static Unsafe unsafe;

    static {
        try {
            Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
            ReflectionUtils.makeAccessible(constructor);
            unsafe = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 一直阻塞
                unsafe.park(false, 0L);
            }
        });
        thread.start();
        thread.interrupt();
    }
}
