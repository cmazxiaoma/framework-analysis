package com.cmazxiaoma.spring.beanpostprocessor;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/13 13:11
 */
@MyVersion("V1")
public interface IAbService {

    @MyVersion("V1")
    void create();

    @MyVersion("V2")
    void retrieve();

    @MyVersion("V1")
    void update();

    @MyVersion("V1")
    void delete();
}
