package com.cmazxiaoma.retry;

import com.cmazxiaoma.controller.HttpUtil;
import com.github.rholder.retry.*;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/6/12 12:26
 */
@RequestMapping("/retry")
@RestController
public class RetryController {


    @GetMapping("/demo")
    public String demo() {
        /**
         * spring-retry工具虽能优雅实现重试，但是存在两个不友好设计：
         * 一个是 重试实体限定为Throwable子类，说明重试针对的是可捕捉的功能异常为设计前提的，
         * 但是我们希望依赖某个数据对象实体作为重试实体，但Spring-retry框架必须强制转换为Throwable子类。
         * 另一个就是重试根源的断言对象使用的是doWithRetry的Exception 异常实例，不符合正常内部断言的返回设计。
         */
        Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
                .retryIfResult(
                        Predicates.or(
                                Predicates.equalTo(null),
                                Predicates.equalTo("fail")
                        )
                )
                .retryIfException()
                .retryIfExceptionOfType(RuntimeException.class)
                .withWaitStrategy(WaitStrategies.fixedWait(500, TimeUnit.MILLISECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                .build();

        try {
            retryer.call(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return com.cmazxiaoma.retry.HttpUtil.doGet("http://localhost:8082/http/demo",
                            "UTF-8", null);
                }
            });
        } catch (ExecutionException ex) {

        } catch (RetryException e) {

        }

        return "success";
    }

    public static void main(String[] args) {
        Predicate predicates = Predicates.equalTo("1");
        Boolean result = predicates.apply("1");
        System.out.println(result);
    }
}
