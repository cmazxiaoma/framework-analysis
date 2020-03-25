package com.cmazxiaoma.concurrent.aqs;

import com.cmazxiaoma.concurrent.CustomThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/20 14:35
 */
public class MyLockTest {

    public static void main(String[] args) {
        test();
    }


    public static void test() {
        MyLock lock = new MyLock();

        CustomThreadPoolExecutor threadPoolExecutor = new CustomThreadPoolExecutor(
                10, 10, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10)
        );

        for (int i = 0; i < 10; i++) {
            CustomThreadPoolExecutor.CustomTask task = new CustomThreadPoolExecutor.CustomTask(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                lock.lock();
                                System.out.println("thread:" + Thread.currentThread().getName() + "lock...");

                                System.out.println("thread:" + Thread.currentThread().getName() + "execute...");

                                System.out.println("thread:" + Thread.currentThread().getName() + "unLock...");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            } finally {
                                try {
                                    TimeUnit.SECONDS.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                lock.unLook();
                            }
                        }
                    }
                    , "success");
            threadPoolExecutor.submit(task);
        }
        threadPoolExecutor.shutdown();
    }

}
