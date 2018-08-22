package com.cmazxiaoma.concurrent.condition;

import com.cmazxiaoma.concurrent.CustomThreadPoolExecutor;
import com.cmazxiaoma.concurrent.lock.MyReentrantLockTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/22 10:26
 */
public class MyBlockingQueue<T> {

    private int maxSize;
    private List<T> data;
    private ReentrantLock lock;
    private Condition putCondition;
    private Condition takeCondtion;

    public MyBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new ArrayList<>(maxSize);
        lock = new ReentrantLock();
        putCondition = lock.newCondition();
        takeCondtion = lock.newCondition();
    }

    public void put(T t) throws InterruptedException {
        lock.lockInterruptibly();

        try {
            while (data.size() >= maxSize) {
                putCondition.await();
            }
            data.add(t);
            takeCondtion.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lockInterruptibly();

        try {
            while (data.size() == 0) {
                takeCondtion.await();
            }
            T item = data.remove(0);
            putCondition.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "MyBlockingQueue{" +
                "maxSize=" + maxSize +
                ", data=" + data +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<>(2);
        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(
                2, 2, 0L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10)
        );

        customThreadPoolExecutor.submit(new ThreadA(myBlockingQueue));
        customThreadPoolExecutor.submit(new ThreadB(myBlockingQueue));

        customThreadPoolExecutor.shutdown();

        TimeUnit.SECONDS.sleep(5);

        System.out.println("data:"+  myBlockingQueue.toString());
    }

    static class ThreadA implements Runnable {
        private MyBlockingQueue<Integer> myBlockingQueue;

        public ThreadA(MyBlockingQueue<Integer> myBlockingQueue) {
            this.myBlockingQueue = myBlockingQueue;
        }

        @Override
        public void run() {
            try {
                myBlockingQueue.put(1);
                myBlockingQueue.put(2);
                myBlockingQueue.put(3);
                myBlockingQueue.put(4);
                myBlockingQueue.put(5);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    static class ThreadB implements Runnable {
        private MyBlockingQueue<Integer> myBlockingQueue;

        public ThreadB(MyBlockingQueue<Integer> myBlockingQueue) {
            this.myBlockingQueue = myBlockingQueue;
        }

        @Override
        public void run() {
            try {
                System.out.println("take:"+  myBlockingQueue.take());
                System.out.println("take:"+  myBlockingQueue.take());
                System.out.println("take:"+  myBlockingQueue.take());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
