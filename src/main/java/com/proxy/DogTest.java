package com.proxy;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/1/29 17:41
 */
public class DogTest {

    public static void main(String[] args) {
        Dog dog = DogFactory.getInstance(new CglibProxy());
        System.out.println(dog.call("123"));
    }
}
