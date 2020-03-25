package com.cmazxiaoma.concurrent.aqs;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/12/7 15:26
 */
public class Test1 {

    public static void main(String[] args) {
        System.out.println(ThreadLocalRandom.current().nextInt(1, 101));
    }
}
