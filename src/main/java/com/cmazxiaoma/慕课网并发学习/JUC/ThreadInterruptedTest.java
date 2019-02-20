package com.cmazxiaoma.慕课网并发学习.JUC;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/15 16:15
 */
public class ThreadInterruptedTest {

    /**
     * 检测线程中断
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
    }
}
