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
 * @date 2018/8/22 9:52
 */
public class ConditionTest2 {

    private static Lock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();

    public static void main(String[] args) {
        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(
                2, 2, 0L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10)
        );

        customThreadPoolExecutor.submit(new ThreadA());
        customThreadPoolExecutor.submit(new ThreadB());

        customThreadPoolExecutor.shutdown();
    }

    private static class ThreadA implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("A执行...");
                conditionB.signal();
                conditionA.await();
            } catch (Exception ex) {
                ex.printStackTrace();
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
                System.out.println("B执行...");
                conditionA.signal();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
