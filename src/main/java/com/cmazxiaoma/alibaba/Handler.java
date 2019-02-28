package com.cmazxiaoma.alibaba;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/2/28 14:09
 */
public interface Handler<K, V> {

    void completed(K result, V attachment);

    void failed(Throwable ex, V attachment);
}
