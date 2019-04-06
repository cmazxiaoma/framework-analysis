package com.cmazxiaoma;

import com.cmazxiaoma.慕课网并发学习.线程安全策略.A;
import com.cmazxiaoma.慕课网并发学习.线程安全策略.B;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/3/22 17:47
 */
public class ClassTest {

    public static void main(String[] args) {
        System.out.println(A.class.isInstance(new A()));
        System.out.println(B.class.isInstance(new A()));
        System.out.println(A.class.isInstance(new B()));
    }
}
