package com.kmob.generator.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 日期处理
 * 
 */
public class DateUtils {
    /**
     * 默认的日期格式 .
     */
    public static final String DEFAULT_REGEX = "yyyy-MM-dd";
    /**
     * 默认的日期格式 .
     */
    public static final String DEFAULT_REGEX_YYYYMMDD = "yyyyMMdd";
    /**
     * 默认的日期格式 .
     */
    public static final String DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认的DateFormat 实例
     */
    private static final EPNDateFormat DEFAULT_FORMAT = new EPNDateFormat(DEFAULT_REGEX);
    /**
     * 默认的DateFormat 实例
     */
    private static final EPNDateFormat DEFAULT_FORMAT_YYYY_MM_DD_HH_MIN_SS = new EPNDateFormat(
            DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS);
    /**
     * 默认的DateFormat 实例
     */
    private static final EPNDateFormat DEFAULT_FORMAT_YYYYMMDD = new EPNDateFormat(DEFAULT_REGEX_YYYYMMDD);
    private static Map<String, EPNDateFormat> formatMap = new HashMap<String, EPNDateFormat>();
    
    private static String REGEX = "\\b\\d{2}[.-]\\d{1,2}([.-]\\d{1,2}){0,1}\\b";
    private static Pattern PARSE_PATTERN = Pattern.compile(REGEX);
            
    
    static {
        formatMap.put(DEFAULT_REGEX, DEFAULT_FORMAT);
        formatMap.put(DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS, DEFAULT_FORMAT_YYYY_MM_DD_HH_MIN_SS);
        formatMap.put(DEFAULT_REGEX_YYYYMMDD, DEFAULT_FORMAT_YYYYMMDD);
    }

    private DateUtils() {

    }

    /**
     * 时间对象格式化成String ,等同于java.text.DateFormat.format();
     * 
     * @param date
     *            需要格式化的时间对象
     * 
     * @return 转化结果
     */
    public static String format(java.util.Date date) {
        return DEFAULT_FORMAT.format(date);
    }

    /**
     * 时间对象格式化成String ,等同于java.text.SimpleDateFormat.format();
     * 
     * @param date
     *            需要格式化的时间对象
     * @param regex
     *            定义格式的字符串
     * 
     * @return 转化结果
     */
    public static String format(java.util.Date date, String regex) {
        return getDateFormat(regex).format(date);
    }

    private static EPNDateFormat getDateFormat(String regex) {
        EPNDateFormat fmt = formatMap.get(regex);
        if (fmt == null) {
            fmt = new EPNDateFormat(regex);
            formatMap.put(regex, fmt);
        }
        return fmt;
    }

    /**
     * 尝试解析时间字符串 ,if failed return null;
     * 
     * @param time
     * 
     * @return
     */
    public static Date parseByAll(String time) {
        Date stamp = null;
        if (time == null || "".equals(time)) {
            return null;
        }
        if (PARSE_PATTERN.matcher(time).matches()) {
            time = (time.charAt(0) == '1' || time.charAt(0) == '0' ? "20" : "19") + time;
        }

        stamp = DateUtils.parse(time, "yyyy-MM-ddHH:mm:ss");
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyy-MM-dd");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyy.MM.dd");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyy-MM");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyy.MM");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyy-MM-dd");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yy-MM-dd");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyy.MM.dd");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyy-MM.dd");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyy.MM-dd");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyyMMdd");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyy年MM月dd日");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyyMM");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyy年MM月");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyy");
        }
        if (stamp == null) {
            stamp = DateUtils.parse(time, "yyyy年");
        }
        return stamp;
    }

    /**
     * 解析字符串成时间 ,遇到错误返回null不抛异常
     * 
     * @param source
     * 
     * @return 解析结果
     */
    public static java.util.Date parse(String source) {
        try {
            return DEFAULT_FORMAT.parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解析字符串成时间 ,遇到错误返回null不抛异常
     * 
     * @param source
     * @param 格式表达式
     * 
     * @return 解析结果
     */
    public static java.util.Date parse(String source, String regex) {
        try {
            EPNDateFormat fmt = getDateFormat(regex);
            return fmt.parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 取得当前时间的Date对象 ;
     * 
     * @return
     */
    public static Date getNowDate() {
        return new Date(System.currentTimeMillis());
    }
    
    /**
     * 获取当前时间字符串
     * 
     * @return
     */
    public static String getNow() {
        return getNow(DEFAULT_REGEX);
    }
    
    /**
     * 获取当前时间字符串
     * 
     * @param regex 格式表达式
     * @return
     */
    public static String getNow(String regex) {
        return format(getNowDate(), regex);
    }

}

class EPNDateFormat {
    private SimpleDateFormat instance;

    EPNDateFormat() {
        instance = new SimpleDateFormat(DateUtils.DEFAULT_REGEX);
        instance.setLenient(false);
    }

    EPNDateFormat(String regex) {
        instance = new SimpleDateFormat(regex);
        instance.setLenient(false);
    }

    synchronized String format(java.util.Date date) {
        if (date == null) {
            return "";
        }
        return instance.format(date);
    }

    synchronized java.util.Date parse(String source) throws ParseException {
        return instance.parse(source);
    }
}
