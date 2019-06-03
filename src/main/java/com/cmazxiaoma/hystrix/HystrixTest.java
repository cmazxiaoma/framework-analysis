package com.cmazxiaoma.hystrix;

import com.netflix.hystrix.*;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.hystrix.contrib.javanica.command.HystrixCommandFactory;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import javafx.scene.paint.Stop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;
import rx.Observable;
import rx.Subscriber;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/27 11:25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HystrixTest {

    @Autowired
    private UserService userService;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class User {
        private Long id;
        private String msg;
    }

    @Test
    public void testMain()
            throws ExecutionException, InterruptedException {
//        UserCommand userCommand =
//                new UserCommand(new HystrixCommandGroupKey() {
//                    @Override
//                    public String name() {
//                        return "group_user";
//                    }
//                });
        // 初始化请求上下文
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
//        UserCommand userCommand =
//                new UserCommand();
//        User result = userCommand.execute();
//        System.out.println(result);
//
//        UserCommand userCommand1 = new UserCommand();
//        User result1 = userCommand1.execute();
//        System.out.println(result1);
//
//        // 清除缓存
//        UserCommand.flushCache(String.valueOf(1L));
//        UserCommand userCommand2 = new UserCommand();
//        User result2 = userCommand2.execute();
//        System.out.println(result2);

//        System.out.println(userService.getUserById(2L));
//        System.out.println(userService.getUserById(2L));
//        System.out.println(userService.getUserById(2L));
//
//        // 清除缓存
//        HystrixRequestCache.getInstance(HystrixCommandKey.Factory.asKey("getUserById"),
//                HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(2L));
//        System.out.println(userService.getUserById(2L));
//
//        System.out.println("====================================");
//
//        System.out.println(userService.getUserByIdV2(3L));
//        System.out.println(userService.getUserByIdV2(3L));
//        System.out.println(userService.getUserByIdV2(3L));
//
//        //清除缓存
//        HystrixRequestCache.getInstance(HystrixCommandKey.Factory.asKey("getUserByIdV2"),
//                HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(3L));
//        System.out.println(userService.getUserByIdV2(3L));

//        // 测试Hystrix Collapser
//        UserCollapseCommand userCollapseCommand = new UserCollapseCommand(userService, 1L);
//        System.out.println(userCollapseCommand.execute());
//
//
//        UserCollapseCommand userCollapseCommand1 = new UserCollapseCommand(userService, 2L);
//        System.out.println(userCollapseCommand1.execute());
//
//        UserCollapseCommand userCollapseCommand2 = new UserCollapseCommand(userService, 3L);
//        System.out.println(userCollapseCommand2.execute());

        // 测试Hystrix Collapser 注解版，必须包装成Future
//        System.out.println(userService.getUserByIdV3(1L));
//        System.out.println(userService.getUserByIdV3(2L));
//        System.out.println(userService.getUserByIdV3(3L));
//        System.out.println(userService.getUserByIdV3(4L));
//        System.out.println(userService.getUserByIdV3(5L));
//
//        context.close();
//        StopWatch stop = new StopWatch();
//        stop.start();
//
//        Future<User> future = userService.getUserByIdAsync(3L);
//
//        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // 获取所有线程的堆栈
//                Map<Thread, StackTraceElement[]> maps = Thread.getAllStackTraces();
//                Set<Thread> threads =  maps.keySet();
//                for (Thread thread : threads) {
//                    System.out.println(thread.toString());
//                }
//                stop.stop();
//                System.out.println("==========运行时间==========");
//                System.out.println(stop.getTotalTimeSeconds());
//            }
//        }));
//
//        System.out.println("========Async Start=========");
//        System.out.println(userService.getUserByIdAsync(3L)
//        .get());
//        System.out.println("========Async End===========");

//        UserCommand userCommand = new UserCommand();
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        System.out.println("=============异步开始===============");
        // queue创建的是一个 【守护线程】
//        Future<User> future = userCommand.queue();
        System.out.println("=========执行中=========");
        new Thread(new BlockThread()).start();

//        Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownThread(stopWatch)));
//
//        UserObservableCommand
//                userObservableCommand = new UserObservableCommand(
//                new HystrixCommandGroupKey() {
//                    @Override
//                    public String name() {
//                        return "UserObservableCommand";
//                    }
//                }
//        );
//        /**
//         * 1.前者返回的是一个Hot Observable，
//         * 该命令会在observe()调用的时候立即执行，
//         * 当Observable每次被订阅的时候会重放它的行为；
//         * 2.而后者返回的是一个Code Observable,
//         * toObservable()执行之后,命令不会被立即执行,
//         * 只有当所有订阅者都订阅它之后才会执行。
//         */
//        Observable<User> observable = userObservableCommand.observe();
//
//        Subscriber<User> subscriber =
//                new Subscriber<User>() {
//                    @Override
//                    public void onCompleted() {
//                        System.out.println("=====onCompleted======");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("======onError========");
//                        System.out.println(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(User user) {
//                        System.out.println("=======onNext======");
//                        System.out.println(user);
//                    }
//                };
//        observable.subscribe(subscriber);

    }
}
