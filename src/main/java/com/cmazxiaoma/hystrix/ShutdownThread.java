package com.cmazxiaoma.hystrix;

import org.springframework.util.StopWatch;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/31 20:17
 */
public class ShutdownThread implements Runnable {

    private StopWatch stopWatch;

    public ShutdownThread(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    @Override
    public void run() {
        stopWatch.stop();
        System.out.println("============耗时:" + stopWatch.getTotalTimeSeconds());
    }
}
