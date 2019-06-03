package com.cmazxiaoma.hystrix;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/31 19:44
 */
public class UserCommand extends HystrixCommand<HystrixTest.User> {

    public UserCommand() {
        super(Setter.withGroupKey(HystrixCommandGroupKey
                .Factory.asKey("UserGroup")).andCommandKey(
                HystrixCommandKey.Factory.asKey("UserCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserThreadPool"))
        );
    }

    public UserCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected HystrixTest.User run() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("=========执行完毕...=============");
        return new HystrixTest.User().setId(1L);
    }

    /**
     * fallback方法修饰符没有特定的要求，定义成public,private,protected
     * @return
     */
    @Override
    protected HystrixTest.User getFallback() {
        return new HystrixTest.User().setId(1L).setMsg("fallback");
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(1L);
    }

    // 清除缓存
    public static void flushCache(String cacheKey) {
        HystrixRequestCache.getInstance(HystrixCommandKey.Factory.asKey("UserCommand"),
                HystrixConcurrencyStrategyDefault.getInstance()
        ).clear(cacheKey);

    }
}
