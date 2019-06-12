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
