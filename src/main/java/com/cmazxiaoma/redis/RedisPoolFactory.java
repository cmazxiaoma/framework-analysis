package com.cmazxiaoma.redis;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/5/9 23:51
 */
@Component
public class RedisPoolFactory extends CachingConfigurerSupport {

    @Autowired
    private RedisConfig redisConfig;

    /**
     * 单机redis配置
     * @return
     */
    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxActive());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait());
        JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout(), redisConfig.getPassword(), 0);
        return jp;
    }


    @Bean
    public JedisSentinelPool jedisSentinelPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxActive());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);

        String[] sentinelArray = redisConfig.getSentinelNodes().split(",");
        Set<String> sentinels = new HashSet<>();
        CollectionUtils.addAll(sentinels, sentinelArray);

        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(
                redisConfig.getSentinelMaster(),
                sentinels,
                poolConfig,
                redisConfig.getSentinelTimeOut(),
                redisConfig.getSentinelPassword()
        );

        return jedisSentinelPool;
    }

    /**
     * redis主从集群+哨兵模式配置
     * @return
     */
    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration() {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        String[] sentinelArray = redisConfig.getSentinelNodes().split(",");

        for (String sentinel : sentinelArray) {
            String[] hostAndPort = sentinel.split(":");
            String host = hostAndPort[0].trim();
            int port = Integer.parseInt(hostAndPort[1].trim());
            redisSentinelConfiguration.addSentinel(new RedisNode(host, port));
        }
        redisSentinelConfiguration.setMaster(redisConfig.getSentinelMaster());
        return redisSentinelConfiguration;
    }


}
