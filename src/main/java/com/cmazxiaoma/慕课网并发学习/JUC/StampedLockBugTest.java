package com.cmazxiaoma.慕课网并发学习.JUC;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.apache.tomcat.util.threads.TaskThreadFactory;
import org.springframework.util.ReflectionUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/15 22:47
 */
public class StampedLockBugTest {

    public static StampedLock stampedLock = new StampedLock();
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

    public static void main(String[] args) throws InterruptedException {
        Thread parkThread= new Thread(new Runnable() {
            @Override
            public void run() {
                Long stamp = stampedLock.writeLock();
                unsafe.park(true, System.currentTimeMillis() + 6000L);
                stampedLock.unlockWrite(stamp);
            }
        });
        TimeUnit.SECONDS.sleep(1L);
        parkThread.start();

        Long start = System.currentTimeMillis();

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyThread());
            threadList.add(thread);
            thread.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }
        Long end = System.currentTimeMillis();
        System.out.println("3个线程阻塞了:" + (end - start) + "ms");
    }

    public static class MyThread implements Runnable {
        @Override
        public void run() {
            Thread.currentThread().interrupt();
            Long stamp = stampedLock.readLock();
            System.out.println(Thread.currentThread().getName() + " read lock");
            stampedLock.unlockRead(stamp);
        }
    }

}
