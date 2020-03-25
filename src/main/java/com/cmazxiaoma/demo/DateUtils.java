package com.cmazxiaoma.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/20 18:25
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {


    public static final ThreadLocal<DateFormat> DATE_FORMAT_THREAD_LOCAL
            = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String format(Date date) {
        return DATE_FORMAT_THREAD_LOCAL.get().format(date);
    }

    public static Date parse(String date) {
        try {
            return DATE_FORMAT_THREAD_LOCAL.get().parse(date);
        } catch (Exception ex) {
            throw new RuntimeException("解析时间失败");
        }
    }

    public static boolean isBetween(Date nowDate, Date startDate, Date endDate) {
        if (nowDate.compareTo(startDate) >= 0 && nowDate.compareTo(endDate) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取日期23点59分59秒
     *
     * @param date
     * @return
     */
    public static Date getLastOfDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        instance.set(Calendar.MILLISECOND, 999);
        return instance.getTime();
    }

    /**
     * 获取日期0点0分00秒
     * @param date
     * @return
     */
    public static Date getFirstOfDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    public static void main(String[] args) throws ParseException {
        Date nowDate = DateUtils.parseDate("2019-10-30 20:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println(DateUtils.getLastOfDate(nowDate).getTime() - nowDate.getTime() + 1L);
        System.out.println(DateUtils.getLastOfDate(nowDate).getTime() - nowDate.getTime());
    }
}
