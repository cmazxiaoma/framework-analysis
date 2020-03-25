package com.cmazxiaoma.quzhuanxiang;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/9/21 15:44
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(100000000L / 225666L);

        Date nowDate = new Date();
        System.out.println(DateUtils.minutesOfTwo(DateUtils.getFirstOfDate(nowDate), nowDate));

        System.out.println( (int) 0 / 3);

        for (int i = 0; i < 10000; i++) {
            Long showCount = 0L;
            int minute = DateUtils.minutesOfTwo(DateUtils.getFirstOfDate(nowDate), nowDate);

            int factor = minute / 3;

            Long falseCount = ThreadLocalRandom.current().nextLong(
                    factor * 80,
                    factor * 120);
            showCount = falseCount;

            if (showCount == 0L) {
                showCount = 10000L;
            }
            // 80 - 120
            Long avgMoneyBeanCount = 100000000L / showCount;
            System.out.println("minute:" + minute + "," + "factor:" + factor + "," + "falseCount:" + falseCount + ","+ "avgMoneyBeanCount:" + avgMoneyBeanCount);
        }
    }
}
