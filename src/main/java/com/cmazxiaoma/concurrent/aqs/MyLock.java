package com.cmazxiaoma.concurrent.aqs;

import org.springframework.util.ReflectionUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.util.concurrent.locks.LockSupport;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/20 14:17
 */
public class  MyLock {

    private volatile int state = 0;

    private MyThreadList myThreadList = new MyThreadList();

    private static Unsafe unsafe;

    private static long stateOffSet;

    static {
        try {
            Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
            ReflectionUtils.makeAccessible(constructor);
            unsafe = constructor.newInstance();
            stateOffSet = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("state"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void lock() {
        if (!compareAndSetState(0, 1)) {
            addNodeAndWait();
        }
    }

    public void addNodeAndWait() {
        for (;;) {
            boolean isOnlyOne = myThreadList.insert(Thread.currentThread());

            if (isOnlyOne && compareAndSetState(0, 1)) {
                return;
            }
            //
            LockSupport.park(this);

            if (compareAndSetState(0, 1)) {
                return;
            }
        }
    }


    public void unLook() {
        compareAndSetState(1, 0);
        Thread thread = myThreadList.pop();

        if (thread != null) {
            LockSupport.unpark(thread);
        }

    }

    private boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffSet, expect, update);
    }
}
