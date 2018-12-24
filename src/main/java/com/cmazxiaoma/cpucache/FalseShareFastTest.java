package com.cmazxiaoma.cpucache;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/26 22:02
 */
public class FalseShareFastTest implements Runnable {

    public static int NUM_THREADS = 4;
    public static final long ITERATIONS = 500 * 1000 * 1000L;
    public final int arrayIndex;
    public static MyVolatileLong[] myVolatileLongs;
    public static long SUM_TIME = 0L;

    public FalseShareFastTest(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(String[] args) throws InterruptedException, IllegalAccessException {
        for (int i = 0; i < 10; i++) {
            System.out.println("i=" + i);

            myVolatileLongs = new MyVolatileLong[NUM_THREADS];
            int length = myVolatileLongs.length;

            for (int j = 0; j < length; j++) {
                myVolatileLongs[j] = new MyVolatileLong();
            }

            System.out.println("myVolatileLongs占有空间大小：" + SizeOfObject.sizeOf(myVolatileLongs));
            System.out.println("myVolatileLongs占有总空间大小：" + SizeOfObject.fullSizeOf(myVolatileLongs));

            long start = System.nanoTime();
            delegateRun();
            long end = System.nanoTime();
            SUM_TIME += end - start;
        }

        System.out.println("avg const time:" + SUM_TIME / 10);
    }

    public static void delegateRun() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseShareFastTest(i));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;

        while (--i != 0) {
            myVolatileLongs[arrayIndex].value = i;
        }
    }

    /**
     * MyVolatileLong分配填充变量后, MyVolatileLong每个对象占有的空间为 12 + 8 * 7 + padding = 72
     * MyVolatileLong未分配填充变量前, MyVolatileLong每个对象占有的空间为 12 + 8 + padding = 24
     *
     */
    public static class MyVolatileLong {
        public volatile long value = 0L;
        public long long1,long2,long3,long4,long5,long6;
    }
}
