package com.cmazxiaoma.quzhuanxiang;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/9/21 14:51
 */
public class GameBoxActivityConstant {

    public static final String GAME_BOX_ACTIVITY_START_TIME = "2019-10-01 00:00:00";

    public static final String GAME_BOX_ACTIVITY_END_TIME = "2019-10-11 00:00:00";

    public static Date GAME_BOX_ACTIVITY_START_DATE;

    public static Date GAME_BOX_ACTIVITY_END_DATE;

    static {
        try {
            GAME_BOX_ACTIVITY_START_DATE = DateUtils.parseDate(GAME_BOX_ACTIVITY_START_TIME, "yyyy-MM-dd HH:mm:ss");
            GAME_BOX_ACTIVITY_END_DATE = DateUtils.parseDate(GAME_BOX_ACTIVITY_END_TIME, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception ex) {
            throw new RuntimeException("初始化GameBoxActivityConstant失败!");
        }
    }

    public static void main(String[] args) {
        System.out.println(GAME_BOX_ACTIVITY_START_DATE);
        System.out.println(GAME_BOX_ACTIVITY_END_DATE);
        System.out.println(DateFormatUtils.format(GAME_BOX_ACTIVITY_START_DATE, "yyyy-MM-dd"));
        System.out.println(DateFormatUtils.format(GAME_BOX_ACTIVITY_END_DATE, "yyyy-MM-dd"));
    }

}
