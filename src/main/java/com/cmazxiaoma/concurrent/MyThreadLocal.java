package com.cmazxiaoma.concurrent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/11/20 12:43
 */
public class MyThreadLocal {

    public static final ThreadLocal<DateFormat> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String format(Date date) {
        return DATE_FORMAT_THREAD_LOCAL.get().format(date);
    }

    public static Date parse(String date) throws ParseException {
        return DATE_FORMAT_THREAD_LOCAL.get().parse(date);

    }

    public static void main(String[] args) {
        String date = format(new Date());
        System.out.println(date);
    }
}
