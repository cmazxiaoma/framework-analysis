package com.cmazxiaoma.date;

import com.cmazxiaoma.quzhuanxiang.DateUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/3/8 13:41
 */
public class LocalTimeTest1 {

    public static void main(String[] args) throws Exception {
        Date currentDate = DateUtils.parseDate("2017-07-01 06:00:00", "yyyy-MM-dd HH:mm:ss");

        long current = currentDate.getTime();

        System.out.println("current:" + current);
        System.out.println("epochMilli:" + currentDate.toInstant().toEpochMilli());

        System.out.println("date:" + LocalDateTime.ofInstant(
                currentDate.toInstant(),
                ZoneId.systemDefault()
        ));

        Date newCurrentDate = new Date(current);
        System.out.println(newCurrentDate);
        System.out.println(new Timestamp(current));

    }
}
