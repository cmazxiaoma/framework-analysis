package com.cmazxiaoma.concurrent;

import java.util.concurrent.*;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/16 10:23
 */
public class CustomThreadPoolExecutor extends ThreadPoolExecutor {


    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public static class CustomTask<V> extends FutureTask<V> {

        public CustomTask(Callable<V> callable) {
            super(callable);
        }

        public CustomTask(Runnable runnable, V result) {
            super(runnable, result);
        }

    }

}
