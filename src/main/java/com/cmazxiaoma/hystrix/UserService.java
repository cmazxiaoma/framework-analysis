package com.cmazxiaoma.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/27 15:27
 */

/**
 * 默认情况下，Hystrix会让相同组名的命令使用同一个线程池，
 * 所以我们需要在创建Hystrix命令时为其指定命令组名来实现默认的线程池划分。
 */
@Service
public class UserService {

    /**
     * arithmetic
     * @param id
     * @return
     */
    @com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand(
            ignoreExceptions = {NullPointerException.class,
                ArithmeticException.class},
            fallbackMethod = "fail",
    commandKey = "getUserById",
    groupKey = "UserGroup",
    threadPoolKey = "getUserByIdThread")
    @CacheResult(cacheKeyMethod = "getUserByIdCacheKey")
    public HystrixTest.User getUserById(Long id) {
        System.out.println("======有没有缓存呢============");
        return new HystrixTest.User().setId(id);
    }

    /**
     * 生成缓存的key
     * @return
     */
    public String getUserByIdCacheKey(Long id) {
        return String.valueOf(id);
    }

    /**
     * 貌似会抛出Method not found: isId
     * @param id
     * @return
     */
    @CacheResult
    @HystrixCommand(
            commandKey = "getUserByIdV2",
            groupKey = "UserGroup",
            threadPoolKey = "getUserByIdV2Thread"
    )
    public HystrixTest.User getUserByIdV2(@CacheKey("id") Long id) {
        System.out.println("========有没有缓存呢==============");
        return new HystrixTest.User().setId(id);
    }


    /**
     * 合并请求, 一定要包装成Future
     * @param id
     * @return
     */
    @HystrixCollapser(batchMethod = "findAll",
            scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = {
                    @HystrixProperty(name = "timerDelayInMilliseconds", value = "3000"),

                    // 该参数用来设置一次请求合并批处理中允许的最大请求数
                    @HystrixProperty(name = "maxRequestsInBatch", value = "100")
    })
    public Future<HystrixTest.User> getUserByIdV3(Long id) {
        return new AsyncResult<HystrixTest.User>() {
            @Override
            public HystrixTest.User invoke() {
                return new HystrixTest.User().setId(id);
            }

            @Override
            public HystrixTest.User get() throws UnsupportedOperationException {
                return invoke();
            }
        };
    }

    /**
     * 批量处理
     */
    @HystrixCommand
    public List<HystrixTest.User> findAll(List<Long> idList) {
        System.out.println("=============Hystrix合并请求...==============");
        return idList.stream().map(id -> {
            return new HystrixTest.User().setId(id);
        }).collect(Collectors.toList());
    }


    /**
     * 降级方法
     * @param id
     * @return
     */
    public HystrixTest.User fail(Long id, Throwable ex) {
        System.out.println("=========fail=============");
        System.out.println("=========异常信息===========");
        System.out.println(ex.getMessage());
        return new HystrixTest.User().setId(1L).setMsg("fail");
    }


    @com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
    public Future<HystrixTest.User> getUserByIdAsync(final Long id) {
        return new AsyncResult<HystrixTest.User>() {
            @Override
            public HystrixTest.User invoke() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return new HystrixTest.User().setId(3L);
            }

            @Override
            public HystrixTest.User get() throws UnsupportedOperationException {
                return invoke();
            }
        };
    }
}
