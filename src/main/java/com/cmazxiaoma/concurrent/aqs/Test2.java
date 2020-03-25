package com.cmazxiaoma.concurrent.aqs;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/12/8 0:13
 */
public class Test2 {

    public static void main(String[] args) throws ParseException {
        System.out.println(Long.valueOf("000512791"));

        Date time = DateUtils.parseDate("201912081021087", "yyyyMMddHHmmssSSS");

        System.out.println(DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss.SSS"));
    }
}
