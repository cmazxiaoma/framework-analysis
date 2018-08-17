package com.cmazxiaoma.concurrent.lock;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/17 17:28
 */
public class MyReentrantLockTest {

    private MyReentrantLock myReentrantLock = new MyReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        MyReentrantLockTest test = new MyReentrantLockTest();
        test.myReentrantLock.lock();
        test.tryLock();
        test.tryLock();
        test.tryLock();
        test.myReentrantLock.unLock();
    }

    public void tryLock() throws InterruptedException {
        myReentrantLock.lock();
    }
}
