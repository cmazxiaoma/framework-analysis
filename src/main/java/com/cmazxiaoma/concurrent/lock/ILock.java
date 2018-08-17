package com.cmazxiaoma.concurrent.lock;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/17 17:03
 */
public interface ILock {

    void lock() throws InterruptedException;

    void unLock();

}
