package com.cmazxiaoma.cpucache;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/26 22:41
 */
public class Test1 {

    public static final long ITERATIONS = 500L * 1000L * 1000L;

    public static void main(String[] args) {
        System.out.println("iterations=" + ITERATIONS);

        int i = 2;

        while (--i != 0) {
            System.out.println("--i while");
        }

        i = 2;

        while (i-- != 0) {
            System.out.println("i-- while");
        }
    }
}
