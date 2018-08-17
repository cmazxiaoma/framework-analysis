package com.cmazxiaoma.concurrent.lock;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/17 17:08
 */
public class MyLockTest {

    private MyLock myLock = new MyLock();

    public static void main(String[] args) throws InterruptedException {
        MyLockTest myLockTest = new MyLockTest();
        myLockTest.myLock.lock();
        myLockTest.tryLock();
        myLockTest.myLock.unLock();
    }

    public void tryLock() throws InterruptedException {
        myLock.lock();
    }
}
