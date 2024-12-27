package com.biz.business.service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 日期工具类
 *
 */
public class DateUtils {

    public final static String YYYYMMDD = "yyyy-MM-dd";
    public final static String DATETIME_PATTERN = "yyyyMMddHHmmss";
    public final static String HHMMSSStar = " 00:00:00";
    public final static String HHMMSSEnd = " 23:59:59";
    public final static String DATETIME_YYMMDD = "yyyyMMdd";
    public final static String DATETIME_PATTERNS = "yyyyMMdd HH:mm:ss";
    public final static String DATETIME_YMD = "yyyyMMdd";
    public final static String DATETIME_YM = "yyyyMM";

    /**
     * 输出当前天日期格式，拼接自定义字符串
     *
     * @param format
     * @param time
     * @return
     */
    public static String localDateTimeToString(String format, String time) {
        String format1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        return format1 + time;
    }

    public static String localDateTimeToString(Integer date, String format, String time) {
        String format1 = date.toString();
        return format1 + time;
    }

    /**
     * 获取当前时间指定格式
     *
     * @param format
     * @return
     */
    public static String localDateTime(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 指定时间与格式的日期
     *
     * @param str
     * @param patter
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String str, String patter) {
        LocalDateTime parse = LocalDateTime.parse(str, DateTimeFormatter.ofPattern(patter));
        return parse;
    }


    /**
     * 当天23：59：59时间戳
     *
     * @return
     */
    public static Long localDateTerminalTime() {
        String dateTimeToString = localDateTimeToString(DATETIME_YYMMDD, HHMMSSEnd);
        LocalDateTime parse = LocalDateTime.parse(dateTimeToString, DateTimeFormatter.ofPattern(DATETIME_PATTERNS));
        long epochSecond = parse.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
        return epochSecond * 1000;
    }

    /**
     * 当天00:00:00时间戳
     *
     * @return
     */
    public static LocalDateTime localDateTerminalTimeStart() {
        String dateTimeToString = localDateTimeToString(DATETIME_YYMMDD, HHMMSSStar);
        LocalDateTime parse = LocalDateTime.parse(dateTimeToString, DateTimeFormatter.ofPattern(DATETIME_PATTERNS));
        return parse;
    }

    /**
     * 当天00:00:00时间戳
     *
     * @return
     */
    public static LocalDateTime localDateTerminalTimeEnd() {
        String dateTimeToString = localDateTimeToString(DATETIME_YYMMDD, HHMMSSEnd);
        LocalDateTime parse = LocalDateTime.parse(dateTimeToString, DateTimeFormatter.ofPattern(DATETIME_PATTERNS));
        return parse;
    }


    /**
     * 当天00:00:00时间戳
     *
     * @return
     */
    public static LocalDateTime localDateTerminalTimeStart(Integer dates) {
        String dateTimeToString = localDateTimeToString(dates, DATETIME_YYMMDD, HHMMSSStar);
        LocalDateTime parse = LocalDateTime.parse(dateTimeToString, DateTimeFormatter.ofPattern(DATETIME_PATTERNS));
        return parse;
    }

    public static int difDayByIntegerDayToNow(Integer dates){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = localDateTerminalTimeStart(dates);
        Duration duration= Duration.between(localDateTime,now);
        long days = duration.toDays();
        return Long.valueOf(days).intValue();
    }


    /**
     * 当天23:59:59时间戳
     *
     * @return
     */
    public static LocalDateTime localDateTerminalTimeEnd(Integer dates) {
        String dateTimeToString = localDateTimeToString(dates, DATETIME_YYMMDD, HHMMSSEnd);
        LocalDateTime parse = LocalDateTime.parse(dateTimeToString, DateTimeFormatter.ofPattern(DATETIME_PATTERNS));
        return parse;
    }


    /**
     * 获取指定天数的YYYYMMDD Integer格式
     *
     * @return
     */
    public static Integer getDaysInteger(LocalDateTime now) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(now.getYear());
        if (now.getMonthValue() < 10) {
            stringBuffer.append("0");
            stringBuffer.append(now.getMonthValue());
        } else {
            stringBuffer.append(now.getMonthValue());
        }
        if (now.getDayOfMonth() < 10) {
            stringBuffer.append("0");
            stringBuffer.append(now.getDayOfMonth());
        } else {
            stringBuffer.append(now.getDayOfMonth());
        }
        return Integer.valueOf(stringBuffer.toString());
    }

    public static Integer todayInteger() {
        return getDaysInteger(LocalDateTime.now());
    }

    /**
     * 获取指定天数的YYYYMM Integer格式
     *
     * @return
     */
    public static Integer getMonthInteger(LocalDateTime now) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(now.getYear());
        if (now.getMonthValue() < 10) {
            stringBuffer.append("0");
            stringBuffer.append(now.getMonthValue());
        } else {
            stringBuffer.append(now.getMonthValue());
        }
        return Integer.valueOf(stringBuffer.toString());
    }

    /**
     * 当天00:00:00时间戳
     *
     * @return
     */
    public static LocalDateTime localDateMonthStart(Integer month) {
        Integer integer = Integer.valueOf(month.toString() + "01");
        String dateTimeToString = localDateTimeToString(integer, DATETIME_YYMMDD, HHMMSSStar);
        LocalDateTime parse = LocalDateTime.parse(dateTimeToString, DateTimeFormatter.ofPattern(DATETIME_PATTERNS));
        return parse;
    }


    /**
     * 当天00:00:00时间戳
     *
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String dateTimeToString) {
        LocalDateTime parse = LocalDateTime.parse(dateTimeToString, DateTimeFormatter.ofPattern(DATETIME_PATTERNS));
        return parse;
    }

    /**
     * 获取当前日期月的第一天
     *
     * @param now
     * @return
     */
    public static Integer getMonthFirstDayInteger(LocalDateTime now) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getMonthInteger(now));
        stringBuffer.append("01");
        return Integer.valueOf(stringBuffer.toString());
    }


    public static LocalDateTime getDateTimeOfTimestamps(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
    }

    public static LocalDateTime getDateTimeOfTimestampms(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    public static Integer getDateTimeOfTimestampInteger(long timestamp) {
        return getDaysInteger(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()));
    }

    /**
     * 获取localdatetime  时间戳
     *
     * @param localDateTime
     * @return
     */
    public static Long getDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }


    public static Integer getMonthLastDayInteger(LocalDateTime now) {
        LocalDateTime LastDay = now.minusMonths(0).with(TemporalAdjusters.lastDayOfMonth());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getMonthInteger(now));
        stringBuffer.append(LastDay.getDayOfMonth());
        return Integer.valueOf(stringBuffer.toString());
    }

    /**
     * Date 转 LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime coverLocalDateTimeByDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }


    public static String coverLocalDateTime(LocalDateTime localDateTime){
        StringBuffer stringBuffer = new StringBuffer();

        if (localDateTime.getHour() < 10) {
            stringBuffer.append("0");
            stringBuffer.append(localDateTime.getHour());
        } else {
            stringBuffer.append(localDateTime.getHour());
        }
        stringBuffer.append(":");
        if (localDateTime.getMinute() < 10) {
            stringBuffer.append("0");
            stringBuffer.append(localDateTime.getMinute());
        } else {
            stringBuffer.append(localDateTime.getMinute());
        }
        stringBuffer.append(" ");
        if (localDateTime.getDayOfMonth() < 10) {
            stringBuffer.append("0");
            stringBuffer.append(localDateTime.getDayOfMonth());
        } else {
            stringBuffer.append(localDateTime.getDayOfMonth());
        }
        stringBuffer.append("-");
        if (localDateTime.getMonthValue() < 10) {
            stringBuffer.append("0");
            stringBuffer.append(localDateTime.getMonthValue());
        } else {
            stringBuffer.append(localDateTime.getMonthValue());
        }

        return stringBuffer.toString();
    }
}
