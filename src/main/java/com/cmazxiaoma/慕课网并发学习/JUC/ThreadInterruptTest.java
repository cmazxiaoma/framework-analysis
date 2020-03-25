package com.cmazxiaoma.慕课网并发学习.JUC;

import com.cmazxiaoma.quzhuanxiang.DateUtils;

import java.util.Date;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/16 14:56
 */
public class ThreadInterruptTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    if (Thread.interrupted()) {
                        break;
                    }
                    System.out.println("i want interrupted");
                }


            }
        });

        thread.start();

        thread.interrupt();

        Date startDate = DateUtils.getDayBefore(new Date(), 7);
        Date endDate = DateUtils.getDayBefore(new Date(), 1);

        System.out.println(startDate + "," + endDate);
    }
}
