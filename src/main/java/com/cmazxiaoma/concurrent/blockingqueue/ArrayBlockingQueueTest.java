package com.cmazxiaoma.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/10/23 9:18
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1);

        blockingQueue.offer("1", 10L, TimeUnit.SECONDS);
        String s = blockingQueue.poll(10L, TimeUnit.SECONDS);
        System.out.println("s=" + s);
        System.out.println(blockingQueue.toString());

        blockingQueue.offer("2", 10L, TimeUnit.SECONDS);
        System.out.println(blockingQueue.toString());
        long end = System.currentTimeMillis();

        System.out.println((end - start) + "ms");
        String s1 = blockingQueue.poll(10L, TimeUnit.SECONDS);

        System.out.println(blockingQueue.size());
    }
}
