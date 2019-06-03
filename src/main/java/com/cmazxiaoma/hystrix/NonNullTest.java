package com.cmazxiaoma.hystrix;

import lombok.NonNull;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/27 14:28
 */
public class NonNullTest {

    public static void main(String[] args) {
        hello(null);
    }


    /**
     * 如果msg为空的话, 会直接在hello方法中进行抛出，而不会继续执行到hello1方法
     * @param msg
     */
    public static void hello(@NonNull String msg) {
        hello1(msg);
    }

    public static void hello1(String msg) {
        System.out.println("=====hello1==========");
        System.out.println(msg);
    }
}
