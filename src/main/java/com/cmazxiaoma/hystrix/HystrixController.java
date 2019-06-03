package com.cmazxiaoma.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/31 15:32
 */
@RequestMapping("/hystrix")
@RestController
public class HystrixController {

    @Autowired
    private UserService userService;

    @GetMapping("/test0")
    public String test0() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<HystrixTest.User> userFuture1 = userService.getUserByIdV3(1L);
        Future<HystrixTest.User> userFuture2 = userService.getUserByIdV3(2L);
        Future<HystrixTest.User> userFuture3 = userService.getUserByIdV3(3L);

        System.out.println(userFuture1.get());
        System.out.println(userFuture2.get());
        System.out.println(userFuture3.get());
        context.close();
        return "ok";
    }
}
