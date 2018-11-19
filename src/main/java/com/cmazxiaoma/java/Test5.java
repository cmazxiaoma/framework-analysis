package com.cmazxiaoma.java;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/16 23:32
 */
public class Test5 {
    public static Test5 test5 = new Test5();

    static {
        System.out.println(1);
    }

    static {
        System.out.println(2);
    }

    {
        System.out.println(3);
    }

    public Test5() {
        System.out.println(4);
    }

    {
        System.out.println(5);
    }

    static {
        System.out.println(7);
    }

    public static void main(String[] args) {
        System.out.println(6);
        new Test5();

        /**
         * 3
         * 5
         * 4
         * 1
         * 2
         * 7
         * 6
         * 3
         * 5
         * 4
         */
    }


}
