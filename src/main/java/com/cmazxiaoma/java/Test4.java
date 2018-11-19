package com.cmazxiaoma.java;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/16 23:25
 */
public class Test4 {

    static {
        System.out.println(1);
    }

    static {
        System.out.println(2);
    }

    {
        System.out.println(3);
    }

    public Test4() {
        System.out.println(4);
    }

    {
        System.out.println(5);
    }

    static {
        System.out.println(7);
    }

    public static Test4 test4 = new Test4();

    public static void main(String[] args) {
        System.out.println(6);
        new Test4();

        //1 2 7 3 5 4 6 3 5 4
    }


}
