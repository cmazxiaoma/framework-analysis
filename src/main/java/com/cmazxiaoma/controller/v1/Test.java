package com.cmazxiaoma.controller.v1;

import com.cmazxiaoma.concurrent.CustomThreadPoolExecutor;
import com.cmazxiaoma.controller.HttpUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/7 16:26
 */
public class Test {

    public static void main(String[] args) {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(100);

        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(
                100, 100, 0L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100)

        );

        for (int i = 0; i < 100; i++) {
            final int finalName = i;
            CustomThreadPoolExecutor.CustomTask task = new CustomThreadPoolExecutor.CustomTask(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                start.await();
                                HttpUtil.get("http://localhost:8081/test/2?name=" + finalName);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            } finally {
                                end.countDown();
                            }
                        }
                    }
            , "success");
            customThreadPoolExecutor.submit(task);
        }

        start.countDown();
        try {
            end.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        customThreadPoolExecutor.shutdown();
    }
}
