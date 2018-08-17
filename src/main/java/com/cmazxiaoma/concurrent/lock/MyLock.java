package com.cmazxiaoma.concurrent.lock;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/17 17:02
 */
public class MyLock implements ILock {

    private boolean isLooked = false;

    @Override
    public synchronized void lock() throws InterruptedException {
        while (isLooked) {
            wait();
        }
        isLooked = true;
    }

    @Override
    public synchronized void unLock() {
        isLooked = false;
        notify();
    }
}
