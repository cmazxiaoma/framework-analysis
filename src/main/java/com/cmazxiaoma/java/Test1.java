package com.cmazxiaoma.java;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/16 23:08
 */
public class Test1 {

    public static void main(String[] args) {
        new Test1();
    }

    {
        int a = 6;
        if (a > 4) {
            System.out.println("test初始化块，局部变量a的值大于4");
        }
    }

    {
        System.out.println("test类的第二个初始化块");
    }

    public Test1() {
        System.out.println("test类的无参数化构造器");
    }
}
