package com.cmazxiaoma.quzhuanxiang;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/9/24 10:33
 */
public class Test2 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(ThreadLocalRandom.current().nextInt(2));
        }

        Date endTime = DateUtils.addHours(DateUtils.getLastOfDate(new Date()), -1);
        System.out.println(DateFormatUtils.format(endTime, "yyyy-MM-dd HH:mm:ss"));

        Date zeroDay = DateUtils.getDayBefore(new Date(), 0);
        System.out.println(DateFormatUtils.format(zeroDay, "yyyy-MM-dd HH:mm:ss"));

        int[] a = new int[] {1,2,3,4,5};

        for (int b: a) {
            System.out.println("b:" + b);
        }
    }
}
