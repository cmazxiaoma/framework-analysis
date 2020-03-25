package com.cmazxiaoma.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/2/5 17:40
 */
public class ZoneTest {

    public static void main(String[] args) throws ParseException {
        String timeStr = "2017-08-24 11:17:10"; // 字面时间
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        // 1503544630000
        Date bjDate = bjSdf.parse(timeStr);  // 解析
        System.out.println("字面时间: " + timeStr + ",按北京时间来解释:" + bjSdf.format(bjDate) + ", " + bjDate.getTime());

        SimpleDateFormat tokyoSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  // 东京
        tokyoSdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));  // 设置东京时区
        // 1503541030000
        Date tokyoDate = tokyoSdf.parse(timeStr); // 解析
        System.out.println("字面时间: " + timeStr + ",按东京时间来解释:" + tokyoSdf.format(tokyoDate) + ", " + tokyoDate.getTime());

    }
}
