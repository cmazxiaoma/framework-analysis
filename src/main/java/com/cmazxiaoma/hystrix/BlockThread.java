package com.cmazxiaoma.hystrix;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/31 20:50
 */
public class BlockThread implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("=============阻塞执行完毕...================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
