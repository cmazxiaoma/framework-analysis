package com.cmazxiaoma.redis;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/28 9:35
 */
public class SeckillKeyPrefix extends BasePrefix {

    public SeckillKeyPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SeckillKeyPrefix seckillKeyPrefix = new SeckillKeyPrefix(0, "seckill");

}
