package com.cmazxiaoma.hystrix;

import com.cmazxiaoma.concurrent.MyThreadLocal;
import com.cmazxiaoma.慕课网并发学习.JUC.StampedLockBugTest;
import com.google.common.collect.Maps;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/28 15:37
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(TimeZone.getDefault().getRawOffset());
        System.out.println("===================================");
        Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(MyThreadLocal.format(date));

        Map<Integer, Integer> map = Maps.newHashMap();
        // 如果key不存在，返回null
        Integer result1 = map.putIfAbsent(1, 1);

        // 如果key存在，返回老数据
        Integer result2 = map.putIfAbsent(1, 1);

        Integer result3 = map.putIfAbsent(1, 2);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

//        new Thread(new BlockThread()).start();

        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        UserCommand userCommand = new UserCommand();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("=============异步开始===============");

        // daemon线程
        Future<HystrixTest.User> future = userCommand.queue();
        Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownThread(stopWatch)));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("=================等待5s===========");
            }
        }).start();

        while (!future.isDone()) {
            System.out.println("守护线程还在执行中...");
        }
    }
}
