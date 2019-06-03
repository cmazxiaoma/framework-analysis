package com.cmazxiaoma.date;

import com.cmazxiaoma.concurrent.MyThreadLocal;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/23 21:47
 */
public class LocalTimeTest {

    public static void main(String[] args) {
        // jdk1.8 线程安全获取系统时间
        System.out.println(LocalDateTime.now());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // parse
        LocalDateTime localDateTime = LocalDateTime.parse("2018-11-23 21:58:00", dateTimeFormatter);
        System.out.println(localDateTime);

        // format
        System.out.println(LocalDateTime.now().format(dateTimeFormatter));

        System.out.println("======================================");
        System.out.println(TimeZone.getDefault().getRawOffset());
        System.out.println("===================================");
        Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(MyThreadLocal.format(date));

        System.out.println("=================================");
        long zero = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println(new Timestamp(zero));

        long max = LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println(new Timestamp(max));


        long current = System.currentTimeMillis();
        long temp = TimeZone.getDefault().getRawOffset();

        long zero1 =  (current + temp) / (1000*3600*24)*(1000*3600*24)
                - temp;
        System.out.println(new Timestamp(zero1));
    }
}
