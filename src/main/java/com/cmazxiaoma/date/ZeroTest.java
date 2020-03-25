package com.cmazxiaoma.date;

import com.cmazxiaoma.quzhuanxiang.DateUtils;

import java.text.ParseException;
import java.time.*;
import java.util.Date;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/2/5 19:32
 */
public class ZeroTest {

    public static void main(String[] args) throws ParseException {
        Date currentDate = DateUtils.parseDate("2017-07-20 06:00:00", "yyyy-MM-dd HH:mm:ss");

        long current = currentDate.getTime();


        System.out.println("current:" + current);

        long day = current / (1000 * 24 * 60 * 60);
        System.out.println(day);

        long zeroCurrent = day * (1000 * 24 * 60 * 60);

        System.out.println(zeroCurrent);

        System.out.println(current - zeroCurrent);


        System.out.println(new Date(current - zeroCurrent));
        System.out.println(Date.from(LocalDateTime.of(1970, 01, 01, 0, 0, 0).toInstant(ZoneOffset.ofHours(8)).plusSeconds((current - zeroCurrent) / 1000)));


        System.out.println(LocalDateTime.of(1970, 01, 01, 0, 0, 0).toInstant(ZoneOffset.ofHours(8)).plusSeconds((current - zeroCurrent) / 1000).toEpochMilli());


    }
}
