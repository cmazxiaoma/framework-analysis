package com.cmazxiaoma.date;

import com.cmazxiaoma.concurrent.MyThreadLocal;
import com.cmazxiaoma.quzhuanxiang.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
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

    public static void main(String[] args) throws ParseException {
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


        Date currentDate = DateUtils.parseDate("2017-07-01 06:00:00", "yyyy-MM-dd HH:mm:ss");


        long current = currentDate.getTime();


        System.out.println("current:" + current);
        long temp = TimeZone.getDefault().getRawOffset();

        System.out.println("temp:" + temp);

        /**
         * 如果t可能本身并不能被1000　*　3600　*　24整除,所以先除后乘其实起了一个取整去余的作用.
         * 例如这里t表示毫秒,那么除以了1000　*　3600　*　24得到天数,
         * 但是可能不是整数天,但是因为t是long型,
         * 那么小数部分没有了,再去乘以1000　*　3600　*　24,就变成整数天数所对应的毫秒了
         */

        // 为什么会出现跨天, 因为有时区的影响
        // 算出 整点的时间戳， 但是由于时区的影响, 要减去temp。
        // long zero1 = (current + temp) / (1000 * 3600 * 24) * (1000 * 3600 * 24) - temp;

        // 这个比较好理解, 因为北京时间下 current是1970-01-01 08:00:00开始的。所以时间戳+时区
        // current 减去 不是整点的时间戳。
        long zero1 = current - (current + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24);

        /**
         *
         */

        System.out.println("0点时间:" + new Timestamp(zero1));

//######################################推导 错误=================================
        ZoneId zoneId = ZoneId.systemDefault();

        System.out.println(zoneId);

        LocalDateTime localDateTime1 = LocalDateTime.now();

        System.out.println("localDateTime1:" + localDateTime1);

        // 之前是1580925675
        // 1580896875
        // 执行后是
        Instant instant3 = localDateTime1.toInstant(ZoneOffset.ofHours(8));

        Instant instant = localDateTime1.toInstant(ZoneOffset.UTC);


        // ZoneDateTime: 包含了时区的完整的日期时间，偏移量是以UTC/格力威治时间为标准
        // 1000_000_000 可读性变强
        Date date1 = Date.from(instant);

        System.out.println("utc/格林威治时间:" + date1);

        System.out.println("utc:" + Date.from(ZonedDateTime.of(localDateTime1, ZoneId.ofOffset("UTC", ZoneOffset.UTC)).toInstant()));

        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println("date转当前时间:" + localDateTime2);

        // LocalDate、LocalDateTime 的now()方法使用的是系统默认时区 不存在Instant.now()的时间问题
        System.out.println("utc instant:" + new Date(instant.toEpochMilli()));

        System.out.println("now instant:" + new Date(Instant.now().toEpochMilli()));
        System.out.println("new Date instant:" + new Date(new Date().toInstant().toEpochMilli()));
        System.out.println("ofHours 8 instant:" + new Date(instant3.toEpochMilli()));

        System.out.println(Instant.ofEpochSecond(365 * 24 * 60 * 60, 100));
        System.out.println(Instant.now().toEpochMilli());
        // ######################################推导结束=====================================
        System.out.println("最后正确的格林威治时间:" + Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(16))));
        System.out.println(LocalDateTime.now());
        System.out.println(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(8)));
        System.out.println(ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(8)));
        System.out.println(Instant.now());
        System.out.println("giao:" + new Date(Instant.now().toEpochMilli()));

        Clock clock = Clock.systemDefaultZone();
        final Instant now = clock.instant();  // called
        System.out.println(now);
        ZoneOffset offset = clock.getZone().getRules().getOffset(now);
        System.out.println(offset);
        System.out.println(clock);
        System.out.println(clock.getZone().getRules());

        // ##############精度问题告一段落
//
//        System.out.println(-15 % -13);
//        System.out.println(new BigDecimal("-14.788").setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    }
}
