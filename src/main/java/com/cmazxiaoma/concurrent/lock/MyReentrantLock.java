package com.cmazxiaoma.concurrent.lock;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/17 17:12
 */
public class MyReentrantLock implements ILock {

    private Thread lockedByThread = null;

    private int count;

    private boolean isLocked = false;

    @Override
    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();

        while (isLocked && thread != lockedByThread) {
            wait();
        }

        isLocked = true;
        count++;
        lockedByThread = thread;
    }

    @Override
    public synchronized void unLock() {
        Thread thread = Thread.currentThread();

        if (thread == lockedByThread) {
            count --;
            if (count == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}
