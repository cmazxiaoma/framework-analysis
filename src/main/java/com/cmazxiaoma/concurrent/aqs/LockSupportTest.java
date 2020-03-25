package com.cmazxiaoma.concurrent.aqs;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/2/13 21:59
 */
public class LockSupportTest {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("thread...");
                LockSupport.park();
                System.out.println("thread done.");
            }
        });

        t.start();
    }
}
