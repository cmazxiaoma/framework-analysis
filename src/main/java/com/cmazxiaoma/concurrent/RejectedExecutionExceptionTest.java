package com.cmazxiaoma.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: 分析抛出RejectedExecutionException问题
 * @date 2018/8/16 14:35
 */
public class RejectedExecutionExceptionTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    /**
     * 提交的任务数量超过其本身最大能处理的任务量
     */
    public static void test1() {
        CustomThreadPoolExecutor customThreadPoolExecutor =
                new CustomThreadPoolExecutor(2, 2,
                        0L,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 13; i++) {
            CustomThreadPoolExecutor.CustomTask customTask
                    = new CustomThreadPoolExecutor.CustomTask(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(60 * 60);
                        System.out.println("线程" + Thread.currentThread().getName()
                                + "正在执行...");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }, "success");

            if (i == 12) {
                // throw RejectedExectionException
                customThreadPoolExecutor.submit(customTask);
            } else {
                customThreadPoolExecutor.submit(customTask);
            }
        }
        customThreadPoolExecutor.shutdown();
    }

    /**
     * 当线程池shutdown()后，会中断空闲线程。但是正在运行的线程和处于阻塞队列等待执行的线程不会中断。
     * shutdown(),不会接收新的线程。
     */
    public static void test2() {
        CustomThreadPoolExecutor customThreadPoolExecutor =
                new CustomThreadPoolExecutor(2, 2,
                        0L,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 2; i++) {
            CustomThreadPoolExecutor.CustomTask customTask
                    = new CustomThreadPoolExecutor.CustomTask(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(60 * 60);
                        System.out.println("线程" + Thread.currentThread().getName()
                                + "正在执行...");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }, "success");
            customThreadPoolExecutor.submit(customTask);
        }
        customThreadPoolExecutor.shutdown();

        CustomThreadPoolExecutor.CustomTask customTask
                = new CustomThreadPoolExecutor.CustomTask(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(60 * 60);
                    System.out.println("线程" + Thread.currentThread().getName()
                            + "正在执行...");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }, "success");

        customThreadPoolExecutor.submit(customTask);
    }

}
