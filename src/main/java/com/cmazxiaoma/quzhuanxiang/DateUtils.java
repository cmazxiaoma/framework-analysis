package com.cmazxiaoma.quzhuanxiang;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Date 2018/11/2
 * @Author LLJ
 * @Description
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

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

    public static Date getFirstOfDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    public static int getMonth(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.MONTH) + 1;
    }

    public static int getMonthDay(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.HOUR);
    }

    public static Date addSecondsOfDate(Date date, int seconds) {
        return new Date(date.getTime() + seconds * 1000);
    }

    private static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    /**
     * 获取某年某月的最后一天
     *
     * @param year  年
     * @param month 月
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar calendar = getCalendarByYearAndMonth(year, month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取某年某月的第一天
     *
     * @param year  年
     * @param month 月
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar calendar = getCalendarByYearAndMonth(year, month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }


    /**
     * 获取某个日期之后，最接近这个日期的周几
     *
     * @param date    日期
     * @param weekDay 周几 0周日 1周一
     * @return
     */
    public static Date getDateFromWeekday(Date date, int weekDay) {
        Calendar calendar = date2Calendar(date);
        int calWeekDay = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.DAY_OF_WEEK, weekDay + 1);
        if (weekDay + 1 <= calWeekDay) {
            //获取下个星期的日期
            calendar.add(Calendar.WEEK_OF_MONTH, 1);
        }
        return calendar.getTime();
    }


    /**
     * 时间段内，按照星期中的某天分割时间段
     *
     * @param startDate
     * @param lastDate
     * @param weekDay
     * @return
     */
    public static List<WeekSpit> spitWeek(Date startDate, Date lastDate, int weekDay) {
        Date start = startDate;
        Date dateFromWeekday = startDate;
        List<WeekSpit> weekSpits = new ArrayList<>();
        while (truncatedCompareTo(start, lastDate, Calendar.DAY_OF_MONTH) <= 0) {
            dateFromWeekday = getDateFromWeekday(start, 3);
            if (truncatedCompareTo(dateFromWeekday, lastDate, Calendar.DAY_OF_MONTH) >= 0) {
                weekSpits.add(new WeekSpit(start, lastDate));
            } else {
                weekSpits.add(new WeekSpit(start, dateFromWeekday));
            }
            start = addDays(dateFromWeekday, 1);
        }
        return weekSpits;
    }

    /**
     * 获取过去n个月的当天日期,如果该月没有对应日期，则获取该月最后一天的时间
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getPastDay(Date date, int n) {
        Calendar calendar = date2Calendar(truncate(date, Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.MONTH, -n);
        return calendar.getTime();
    }

    /**
     * 获取环比时间
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param n
     * @return
     */
    public static List<WeekSpit> getRingDate(Date startDate, Date endDate, int n) {
        //计算开始时间与结束时间相差的天数
        Calendar startCalendar = date2Calendar(truncate(startDate, Calendar.DAY_OF_MONTH));
        Calendar endCalendar = date2Calendar(truncate(endDate, Calendar.DAY_OF_MONTH));
        /*long start = startCalendar.getTimeInMillis();
        long end = endCalendar.getTimeInMillis();
        int day = (int) ((end - start) / (1000 * 3600 * 24) + 1);*/
        int day = getDiffDay(startCalendar, endCalendar);
        List<WeekSpit> weekSpits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            weekSpits.add(new WeekSpit(startCalendar.getTime(), endCalendar.getTime()));
            startCalendar.add(Calendar.DAY_OF_MONTH, -day);
            endCalendar.add(Calendar.DAY_OF_MONTH, -day);
        }
        return weekSpits.stream().sorted(Comparator.comparing(WeekSpit::getStartDate)).collect(Collectors.toList());
    }

    /**
     * 根据分页信息筛选出时间段内的时间
     *
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @param asc         时间升序还是降序
     * @return
     */
    public static List<Date> getPageDateList(Date startDate, Date endDate, int currentPage, int pageSize, boolean asc) {
        int diffDay = getDiffDay(startDate, endDate);
        int start = pageSize * (currentPage - 1);
        int end = start + pageSize;

        if (start >= diffDay) {
            return new ArrayList<>();
        }

        if (end > diffDay) {
            end = diffDay;
        }

        List<Date> allDates = getAllDates(startDate, endDate, asc);
        return allDates.subList(start, end);
    }

    /**
     * 获取两个时间段共有多少天
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static int getDiffDay(Date startDate, Date endDate) {
        return getDiffDay(date2Calendar(truncate(startDate, Calendar.DAY_OF_MONTH)),
                date2Calendar(truncate(endDate, Calendar.DAY_OF_MONTH)));

    }

    public static int getDiffDay(Calendar startDate, Calendar endDate) {
        long start = startDate.getTimeInMillis();
        long end = endDate.getTimeInMillis();
        int day = (int) ((end - start) / (1000 * 3600 * 24) + 1);
        return day;
    }

    /**
     * 获取同比时间
     *
     * @param startDate
     * @param endDate
     * @param n
     * @return
     */
    public static List<WeekSpit> getSameDate(Date startDate, Date endDate, int n) {
        List<WeekSpit> weekSpits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            weekSpits.add(new WeekSpit(getPastDay(startDate, i), getPastDay(endDate, i)));
        }
        return weekSpits.stream().sorted(Comparator.comparing(WeekSpit::getStartDate)).collect(Collectors.toList());
    }


    public static Calendar date2Calendar(Date date) {
        return new Calendar.Builder().setInstant(date).build();
    }

    public static List<Date> getAllDates(Date startDate, Date endDate, boolean asc) {
        Calendar startCalendar = date2Calendar(truncate(startDate, Calendar.DAY_OF_MONTH));
        Calendar endCalendar = date2Calendar(truncate(endDate, Calendar.DAY_OF_MONTH));

        Calendar current = startCalendar;
        List<Date> dates = new ArrayList<>();
        while (current.compareTo(endCalendar) <= 0) {
            dates.add(current.getTime());
            current.add(Calendar.DAY_OF_MONTH, 1);
        }
        dates.sort(asc ? Comparator.naturalOrder() : Comparator.reverseOrder());
        return dates;
    }


    /**
     * 获取n天前的时间
     *
     * @return
     */
    public static Date getDayBefore(Date nowDate, int n) {
        nowDate = truncate(nowDate, Calendar.DAY_OF_MONTH);
        Calendar nowCalendar = date2Calendar(nowDate);
        nowCalendar.add(Calendar.DAY_OF_MONTH, -n);
        return nowCalendar.getTime();
    }


    private static Calendar getCalendarByYearAndMonth(int year, int month) {
        return new Calendar.Builder()
                .set(Calendar.YEAR, year)
                .set(Calendar.MONTH, month - 1)
                .build();
    }


    public static class WeekSpit {
        private Date startDate;
        private Date endDate;


        public WeekSpit(Date startDate, Date endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public WeekSpit() {
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }
    }

    public static final String PATTERN_DATE_SHORT = "yyyyMMdd";
    public static final String PATTERN_DATE_MS = "yyMMddHHmmssss";
    public static final String PATTERN_DATE_FULL_MS = "yyyyMMddHHmmss";
    public static final String PATTERN_DATE_HH = "yyMMddHH";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_MONTH = "yyyy-MM";
    public static final String PATTERN_DATE_TIME_MS = "yyyy-MM-dd HH:mm:ssss";
    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE_MINUTES = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_DATE_TIME_SHORT = "yyyy-MM-dd HH-mm-ss";

    /**
     * @param d
     * @return
     * @Title dateToStryyyymmddhhmmssss
     * @Description 格式化日期 yyyy-MM-dd HH:mm:ssss
     * @dateTime 2014-12-2 下午2:47:49
     */
    public static String dateToStryyyymmddhhmmssss(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE_TIME_MS);
        return dateFormat.format(d);
    }

    /**
     * 返回格式：yyyyMMdd
     * @param d
     * @return
     */
    public static String dateToStryyyymmdd(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE_SHORT);
        return dateFormat.format(d);
    }

    public static Date stryyyymmddhhmmssToDate(String date) throws ParseException {
        return formatStringToDate(date, PATTERN_DATE_TIME);
    }

    /**
     *  格式：yyyyMMddHHmmss
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String date) throws ParseException {
        return formatStringToDate(date, PATTERN_DATE_FULL_MS);
    }

    /**
     * @param dateString
     * @return
     * @throws ParseException
     * @Title formatStringToDateDefault
     * @Description 格式化日期
     * @dateTime 2014-12-2 下午2:50:35
     */
    public static Date formatStringToDateDefault(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE_TIME);
        return dateFormat.parse(dateString);
    }

    public static String formatDateToStringDefault(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE_TIME);
        return dateFormat.format(d);
    }

    /**
     * @param d
     * @return
     * @Title formatDateToStringShort
     * @Description 格式化日期
     * @dateTime 2014-12-2 下午2:49:27
     */
    public static String formatDateToStringShort(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE);
        return dateFormat.format(d);
    }


    /**
     * @param dateString
     * @param formatPattern
     * @return
     * @throws ParseException
     * @Title formatStringToDate
     * @Description 格式化日期
     * @dateTime 2014-12-2 下午2:50:21
     */
    public static Date formatStringToDate(String dateString, String formatPattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
        return dateFormat.parse(dateString);
    }

    /**
     * @param nowDate   要比较的时间
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return true在时间段内，false不在时间段内
     * @throws Exception
     */
    public static boolean hourMinuteBetween(Date nowDate, Date startDate, Date endDate) {
        return nowDate.getTime() >= startDate.getTime() && nowDate.getTime() <= endDate.getTime();
    }

    public static Date getYesterdayLastOfDate(Date date){
        return addSecondsOfDate(getFirstOfDate(date), -1);
    }

    public static String getYesterdayLastStrOfDate(Date date){
        return formatDateToStringDefault(addSecondsOfDate(getFirstOfDate(date), -1));
    }

    public static String getFirstStrOfDate(Date date){
        return formatDateToStringDefault(getFirstOfDate(date));
    }

    public static int getTodayEnd(){
        //今天零点零分零秒的毫秒数
        long zero = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        //今天23点59分59秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;
        //当前时间毫秒数
        long current = System.currentTimeMillis();
        return (int)((twelve - current) / 1000);
    }

    /**
     *  计算两个日期相差几天，粗略的
     *  会将两个日期转成 xxxx-yy-dd 00:00:00 进行相减
     * @param startDate
     * @param endDate
     * @return
     */
    public static int daysOfTwo_2(Date startDate, Date endDate){
        long end =  getFirstOfDate(endDate).getTime();
        long start =  getFirstOfDate(startDate).getTime();
        long sum = ( end- start);
        long i =  sum/ (1000 * 3600 * 24L);
        return (int) i;
    }

    public static int minutesOfTwo(Date startDate, Date endDate) {
        long end = endDate.getTime();
        long start = startDate.getTime();
        long sum = end - start;
        long i =  sum / (1000 * 60L);
        return (int) i;
    }

    /**
     * #####归档数据专用#######
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<WeekSpit> getAllDateWeekSpit(Date startDate, Date endDate) {
        List<WeekSpit> weekSpitList = Lists.newArrayList();
        List<Date> dateList = getAllDates(startDate, endDate, true);
        for (Date date : dateList) {
            for (int i = 0; i < 24; i++) {
                WeekSpit weekSpit = new WeekSpit();
                Calendar startInstance = Calendar.getInstance();
                startInstance.setTime(date);
                startInstance.set(Calendar.HOUR_OF_DAY, i);
                startInstance.set(Calendar.MINUTE, 0);
                startInstance.set(Calendar.SECOND, 0);
                startInstance.set(Calendar.MILLISECOND, 0);
                weekSpit.setStartDate(startInstance.getTime());

                Calendar endInstance = Calendar.getInstance();
                endInstance.setTime(date);
                endInstance.set(Calendar.HOUR_OF_DAY, i);
                endInstance.set(Calendar.MINUTE, 59);
                endInstance.set(Calendar.SECOND, 59);
                endInstance.set(Calendar.MILLISECOND, 999);
                weekSpit.setEndDate(endInstance.getTime());

                weekSpitList.add(weekSpit);
            }
        }
        return weekSpitList;
    }

    public static void main(String[] args) throws ParseException {
        Long dayLongTime = 86400000L;
        String testDate = "2019-07-12 06:00:00";
        String testDate2 = "2019-07-12 05:00:00";
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("时间：" + (s.parse(testDate).getTime() - s.parse(testDate2).getTime()));
        System.out.println("间隔日期：" + (System.currentTimeMillis() - s.parse(testDate2).getTime())/dayLongTime);
        System.out.println("间隔日期：" + daysOfTwo_2(s.parse(testDate2), s.parse(testDate)));
    }

}
