package com.cmazxiaoma.redis;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/5/10 13:54
 */
@Service
@Slf4j
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取单个对象
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    public boolean lock(KeyPrefix prefix, String key, String value) {
        Jedis jedis = null;
        Long lockWaitTimeOut = 200L;
        Long deadTimeLine = System.currentTimeMillis() + lockWaitTimeOut;

        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;

            for (;;) {
                if (jedis.setnx(realKey, value) == 1) {
                    return true;
                }

                String currentValue = jedis.get(realKey);

                // if lock is expired
                if (!StringUtils.isEmpty(currentValue) &&
                        Long.valueOf(currentValue) < System.currentTimeMillis()) {
                    // gets last lock time
                    String oldValue = jedis.getSet(realKey, value);

                    if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                        return true;
                    }
                }

                lockWaitTimeOut = deadTimeLine - System.currentTimeMillis();

                if (lockWaitTimeOut <= 0L) {
                    return false;
                }
            }
        } finally {
            returnToPool(jedis);
        }
    }


    public void unlock(KeyPrefix prefix, String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String currentValue = jedis.get(realKey);

            if (!StringUtils.isEmpty(currentValue)
                    && value.equals(currentValue)) {
                jedis.del(realKey);
            }
        } catch (Exception ex) {
            log.info("unlock error");
        } finally {
            returnToPool(jedis);
        }
    }

    public boolean lock1(KeyPrefix prefix, String key, String value, Long lockExpireTimeOut,
                         Long lockWaitTimeOut) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            Long deadTimeLine = System.currentTimeMillis() + lockWaitTimeOut;

            for (;;) {
                String result = jedis.set(realKey, value, "NX", "PX", lockExpireTimeOut);

                if ("1".equals(result)) {
                    return true;
                }

                lockWaitTimeOut = deadTimeLine - System.currentTimeMillis();

                if (lockWaitTimeOut <= 0L) {
                    return false;
                }
            }
        } catch (Exception ex) {
            log.info("lock error");
        } finally {
            returnToPool(jedis);
        }

        return false;
    }

    public boolean unlock1(KeyPrefix prefix, String key, String value) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;

            String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

            Object result = jedis.eval(luaScript, Collections.singletonList(realKey),
                    Collections.singletonList(value));

            if ("1".equals(result)) {
                return true;
            }

        } catch (Exception ex) {
            log.info("unlock error");
        } finally {
            returnToPool(jedis);
        }
        return false;

    }

    /**
     * 设置对象
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);

            if (StringUtils.isEmpty(str)) {
                return false;
            }

            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();

            if (seconds <= 0) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 判断key是否存在
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 删除
     * @param prefix
     * @param key
     * @return
     */
    public boolean delete(KeyPrefix prefix, String key) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            long ret = jedis.del(realKey);
            return ret > 0;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 删除指定KeyPrefix的所有值
     * @param prefix
     * @return
     */
    public boolean delete(KeyPrefix prefix) {
        if (Objects.isNull(prefix)) {
            return false;
        }

        List<String> keys = scanKeys(prefix.getPrefix());

        if (CollectionUtils.isEmpty(keys)) {
            return true;
        }
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.del(keys.toArray(new String[0]));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        } finally {
            returnToPool(jedis);
        }

    }

    /**
     * 通过游标，获取所有相似的key
     * @param key
     * @return
     */
    public List<String> scanKeys(String key) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            List<String> keys = new ArrayList<>();
            String cursor = "0";
            ScanParams sp = new ScanParams();
            sp.match("*" + key + "*");
            sp.count(100);

            do {
                ScanResult<String> ret = jedis.scan(cursor, sp);
                List<String> result = ret.getResult();

                if (!CollectionUtils.isEmpty(result)) {
                    keys.addAll(result);
                }
                //再处理cursor
                cursor = ret.getStringCursor();
            } while (!"0".equalsIgnoreCase(cursor));
            return keys;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 将实体类转换成JSON字符串
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {
        if (Objects.isNull(value)) {
            return null;
        }
        Class<?> clazz = value.getClass();

        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * string转换成bean
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || Objects.isNull(clazz)) {
            return null;
        }

        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (!Objects.isNull(jedis)) {
            jedis.close();
        }
    }
}
