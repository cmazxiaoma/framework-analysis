package com.cmazxiaoma.yunshuanfu;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/8/17 11:55
 */
public class Test {


    public static void main(String[] args) {

        if (!isFlag()) {
            System.out.println("false");
        }

        if (isFlag() && print()) {
            System.out.println("gg");
        }

    }

    public static boolean isFlag() {
        return false;
    }

    public static boolean print() {
        System.out.println("123");
        return true;
    }
}
