package com.cmazxiaoma.concurrent.aqs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/2/13 21:47
 */
public class MyLockTest2 {

    private static List<Integer> list = new ArrayList<>();
    private static MyLock myLock = new MyLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    add(i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                print();
            }
        });
        t1.start();
        t2.start();
    }

    private static void add(int i) {
        myLock.lock();
        list.add(i);
        myLock.unLook();
    }

    private static void print() {
        myLock.lock();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        myLock.unLook();
    }

}
