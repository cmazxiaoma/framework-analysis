package com.cmazxiaoma.redis;

import com.cmazxiaoma.InitSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.*;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/9/10 16:06
 */
public class RedisTest extends InitSpringTest {

    @Autowired
    private JedisSentinelPool jedisSentinelPool;

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testJedisSentinelPool() {
        HostAndPort hostAndPort = jedisSentinelPool.getCurrentHostMaster();

        System.out.println("=====>hostAndPort=" + hostAndPort);

        Jedis jedis = null;

        try {
            jedis = jedisSentinelPool.getResource();
            jedis.del("name");
            jedis.set("name", "SUCCESS");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Test
    public void testJedisPool() {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.del("name");
            jedis.set("name", "jedisSentinel");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
