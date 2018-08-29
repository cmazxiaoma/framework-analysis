package com.cmazxiaoma.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/28 9:27
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private static LongAdder longAdder = new LongAdder();
    private static Long LOCK_EXPIRE_TIME = 200L;
    private static Long stock = 10000L;

    @Autowired
    private RedisService redisService;

    static {
        longAdder.add(10000L);
    }

    @GetMapping("/v1/seckill")
    public String seckillV1() {
        Long time = System.currentTimeMillis() + LOCK_EXPIRE_TIME;
        if (!redisService.lock(SeckillKeyPrefix.seckillKeyPrefix, "redis-seckill", String.valueOf(time))) {
            return "人太多了，换个姿势操作一下";
        }

        if (longAdder.longValue() == 0L) {
            return "已抢光";
        }

        doSomeThing();

        if (longAdder.longValue() == 0L) {
            return "已抢光";
        }

        longAdder.decrement();

        redisService.unlock(SeckillKeyPrefix.seckillKeyPrefix, "redis-seckill", String.valueOf(time));

        Long stock = longAdder.longValue();
        Long bought = 10000L - stock;
        return "已抢" + bought + ", 还剩下" + stock;
    }

    @GetMapping("/detail")
    public String detail() {
        Long stock = longAdder.longValue();
        Long bought = 10000L - stock;
        return "已抢" + bought + ", 还剩下" + stock;
    }

    @GetMapping("/v2/seckill")
    public String seckillV2() {
        if (longAdder.longValue() == 0L) {
            return "已抢光";
        }

        doSomeThing();

        if (longAdder.longValue() == 0L) {
            return "已抢光";
        }

        longAdder.decrement();

        Long stock = longAdder.longValue();
        Long bought = 10000L - stock;
        return "已抢" + bought + ", 还剩下" + stock;
    }

    @GetMapping("/v3/seckill")
    public String seckillV3() {
        if (stock == 0) {
            return "已抢光";
        }

        doSomeThing();
        stock--;

        Long bought = 10000L - stock;
        return "已抢" + bought + ", 还剩下" + stock;
    }


    public void doSomeThing() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


}
