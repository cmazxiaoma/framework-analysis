package com.cmazxiaoma.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
    private static Long TIME_OUT = 1000L;

    @Autowired
    private RedisService redisService;

    static {
        longAdder.add(10000L);
    }

    @GetMapping("/seckill")
    public String seckill() {
        Long time = System.currentTimeMillis() + TIME_OUT;
        if (!redisService.lock(SeckillKeyPrefix.seckillKeyPrefix, "redis-seckill", String.valueOf(time))) {
            return "人太多了，换个姿势操作一下";
        }

        if (longAdder.intValue() == 0) {
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

}
