package com.cmazxiaoma.concurrent;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/12/27 13:32
 */
public class CheckLockTest {

    private static final Object object = new Object();

    public static void main(String[] args) {

        synchronized (object) {
            // 判断是否获取object锁
            System.out.println("get lock result:" + Thread.holdsLock(object));
        }

        System.out.println("get lock result:" + Thread.holdsLock(object));
    }
}
