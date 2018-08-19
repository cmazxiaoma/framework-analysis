package com.cmazxiaoma.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/17 17:57
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        final Semaphore semaphore = new Semaphore(5);

        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(
                10, 10, 0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(100)
        );

        for (int i = 0; i < 9; i++) {

            CustomThreadPoolExecutor.CustomTask task =
                    new CustomThreadPoolExecutor.CustomTask(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                semaphore.acquire();
                                System.out.println("thread:" + Thread.currentThread().getName() + "acquire one permit");
                                TimeUnit.MILLISECONDS.sleep(500);
                                System.out.println("thread:" +Thread.currentThread().getName() + "release one permit");
                                semaphore.release();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }, "success");

            customThreadPoolExecutor.submit(task);
        }

        customThreadPoolExecutor.shutdown();
    }

}
