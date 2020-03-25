package com.cmazxiaoma.concurrent;


import java.util.concurrent.*;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/16 9:54
 */
public class CountDownLatchTest {

    /**
     * 类似计数器的功能，比如有一个任务A，需要等待其他5个任务执行完毕后才能执行
     * ，就可以用CountDownLatch
     *
     * @param args
     */
    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        ExecutorService executorService = new CustomThreadPoolExecutor(2,
                2, 0L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 2; i++) {
            CustomThreadPoolExecutor.CustomTask task = new CustomThreadPoolExecutor.CustomTask(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println("子线程" + Thread.currentThread().getName()
                                + "正在执行...");
                        System.out.println("子线程" + Thread.currentThread().getName()
                                + "执行完毕...");
                        countDownLatch.countDown();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }, "success");
            executorService.submit(task);
        }

        try {
            System.out.println("等待2个线程...");
            countDownLatch.await();
            executorService.shutdown();
            System.out.println("2个线程执行完毕...");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void test2() {
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(10);

        ExecutorService executorService = new CustomThreadPoolExecutor(10,
                10, 0L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 10; i++) {
            CustomThreadPoolExecutor.CustomTask task = new CustomThreadPoolExecutor.CustomTask(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("子线程" + Thread.currentThread().getName()
                                + "正在执行...");
                        start.await();
                        System.out.println("子线程" + Thread.currentThread().getName()
                                + "执行完毕...");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            }, "success");
            executorService.submit(task);
        }

        start.countDown();
        try {
            System.out.println("等待10个线程...");
            end.await();
            executorService.shutdown();
            System.out.println("10个线程执行完毕...");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
