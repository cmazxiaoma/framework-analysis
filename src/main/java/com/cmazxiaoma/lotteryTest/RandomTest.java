package com.cmazxiaoma.lotteryTest;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/10/24 20:11
 */
public class RandomTest {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println((int) ThreadLocalRandom.current().nextDouble(3, 4));
        }
    }
}
