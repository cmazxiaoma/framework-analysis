package com.cmazxiaoma.concurrent.condition;

import com.cmazxiaoma.concurrent.CustomThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/22 9:30
 */
public class ConditionTest {

    private static Lock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(
                3, 3, 0L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10)
        );

        customThreadPoolExecutor.submit(new ThreadA());

        customThreadPoolExecutor.submit(new ThreadB());

        customThreadPoolExecutor.submit(new ThreadC());

        customThreadPoolExecutor.shutdown();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("data:" + customThreadPoolExecutor.toString());
    }


    private static class ThreadA implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("线程A执行...");
                conditionB.signal();
                conditionA.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class ThreadB implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("线程B执行...");
                conditionC.signal();
                conditionB.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class ThreadC implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("线程C执行...");
                conditionA.signal();
                conditionC.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
