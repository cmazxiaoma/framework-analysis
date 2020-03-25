package com.proxy;

import org.mockito.cglib.proxy.Enhancer;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/1/29 17:39
 */
public class DogFactory {

    public static Dog getInstance(CglibProxy cglibProxy) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        enhancer.setCallback(cglibProxy);
        Dog dog = (Dog) enhancer.create();
        return dog;
    }
}
