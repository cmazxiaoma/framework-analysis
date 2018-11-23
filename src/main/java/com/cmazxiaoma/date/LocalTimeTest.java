package com.cmazxiaoma.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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


    }
}
