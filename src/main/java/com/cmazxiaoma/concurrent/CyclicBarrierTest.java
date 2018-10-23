package com.cmazxiaoma.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/16 11:49
 */
public class CyclicBarrierTest {

    /**
     * 通过CyclicBarrier可以实现让一组线程等待至某个状态后，再全部同时执行。
     * 为什么叫Cyclic,当所有线程都被释放结束后，CyclicBarrier可以被重用。
     * 当调用await()方法后，所有线程都位于barrier，也就是屏障位置。
     * @param args
     */
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();

        test4();
    }


    /**
     * 挂起当前线程，直到所有线程到达屏障状态后，再同时执行后续任务
     */
    public static void test1() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        ExecutorService executorService = new CustomThreadPoolExecutor(2,
                2, 0L,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 2; i++) {
            CustomThreadPoolExecutor.CustomTask task = new CustomThreadPoolExecutor.CustomTask(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                System.out.println("线程" + Thread.currentThread().getName()
                                    + "正在写入数据...");
                                TimeUnit.MILLISECONDS.sleep(100);

                                System.out.println("线程" + Thread.currentThread().getName()
                                        + "写入数据完毕...");
                                cyclicBarrier.await();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            } catch (BrokenBarrierException ex) {
                                ex.printStackTrace();
                            }

                            System.out.println("所有线程执行完毕...继续执行其他任务...");
                        }

                    }
            , "success");
            executorService.submit(task);
        }

        executorService.shutdown();
    }


    /**
     * 挂起当前线程，直到所有线程到达屏障状态后，再执行后续任务。
     * 当所有线程到达屏障状态后，还可以执行额外的操作。
     */
    public static void test2() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("选择线程" + Thread.currentThread().getName() + "...");
                System.out.println("执行额外的骚操作...");
            }
        });

        ExecutorService executorService = new CustomThreadPoolExecutor(2,
                2, 0L,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 2; i++) {
            CustomThreadPoolExecutor.CustomTask task = new CustomThreadPoolExecutor.CustomTask(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                System.out.println("线程" + Thread.currentThread().getName()
                                        + "正在写入数据...");
                                TimeUnit.MILLISECONDS.sleep(100);

                                System.out.println("线程" + Thread.currentThread().getName()
                                        + "写入数据完毕...");
                                cyclicBarrier.await();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            } catch (BrokenBarrierException ex) {
                                ex.printStackTrace();
                            }

                            System.out.println("所有线程执行完毕...继续执行其他任务...");
                        }

                    }
                    , "success");
            executorService.submit(task);
        }

        executorService.shutdown();
    }

    /**
     * 还可以让其他线程等待一段时间，如果还有线程没有到达屏障状态的话，就直接进行执行后续任务。
     */
    public static void test3() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        ExecutorService executorService = new CustomThreadPoolExecutor(2,
                2, 0L,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 2; i++) {
            CustomThreadPoolExecutor.CustomTask task = new CustomThreadPoolExecutor.CustomTask(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                System.out.println("线程" + Thread.currentThread().getName()
                                        + "正在写入数据...");
                                TimeUnit.MILLISECONDS.sleep(1000);

                                System.out.println("线程" + Thread.currentThread().getName()
                                        + "写入数据完毕...");
                                cyclicBarrier.await(1000, TimeUnit.MILLISECONDS);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                                // 一个线程试图在一个处于崩溃状态的屏障等待其他线程
                            } catch (BrokenBarrierException ex) {
                                ex.printStackTrace();
                            } catch (TimeoutException ex) {
                                System.out.println("等待超时...");
                            }

                            System.out.println("所有线程执行完毕...继续执行其他任务...");
                        }

                    }
                    , "success");

            if (i != 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            executorService.submit(task);
        }

        executorService.shutdown();

        System.out.println("132323");
    }

    /**
     * 重用CyclicBarrier，而CountDownLatch无法做到
     */
    public static void test4() {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        ExecutorService executorService = new CustomThreadPoolExecutor(2,
                2, 0L,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(10));

        for (int j = 0; j < 2; j++) {
            List<Future> futureList = new ArrayList<>();

            for (int i = 0; i < 2; i++) {
                CustomThreadPoolExecutor.CustomTask task = new CustomThreadPoolExecutor.CustomTask(
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.println("线程" + Thread.currentThread().getName()
                                            + "正在写入数据...");
                                    TimeUnit.MILLISECONDS.sleep(100);

                                    System.out.println("线程" + Thread.currentThread().getName()
                                            + "写入数据完毕...");
                                    cyclicBarrier.await();
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                } catch (BrokenBarrierException ex) {
                                    ex.printStackTrace();
                                }

                                System.out.println("所有线程执行完毕...继续执行其他任务...");
                            }

                        }
                        , "success");
                futureList.add(executorService.submit(task));
            }

            while (true) {
                boolean isShutDown = true;

                for (Future future : futureList) {
                    if (!future.isDone()) {
                        isShutDown = false;
                    }
                }

                if (isShutDown) {
                    System.out.println("第" + (j + 1) + "波CyclicBarrier完毕...");
                    break;
                }
            }
        }
        executorService.shutdown();
        System.out.println(executorService.toString());
    }
}
