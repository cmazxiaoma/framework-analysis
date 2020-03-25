package com.cmazxiaoma.quzhuanxiang.lei;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/10/23 18:10
 */
public class B extends A {

    public B() {
        super();
        i = 2;
    }

    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.i);
    }
}
