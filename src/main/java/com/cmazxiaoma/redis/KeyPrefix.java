package com.cmazxiaoma.redis;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/5/10 12:35
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
