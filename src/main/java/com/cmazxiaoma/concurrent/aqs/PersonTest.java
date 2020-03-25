package com.cmazxiaoma.concurrent.aqs;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/9/11 15:29
 */
public class PersonTest {

    public static void main(String[] args) {
        Person person = message -> System.out.println("say Hello:" + message);
        person.sayHello("123");
    }
}
