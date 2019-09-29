package com.neusoft.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.hutool.core.date.DatePattern;

/**
 * 转换：Calendar<->String
 *
 * @author：yu8home
 * @date：2017年1月5日 上午11:12:33
 */
public class CalendarUtils {

    /**
     * 默认日期格式：yyyy-MM-dd
     */
    public static Calendar parseCalendar(String dateStr) {
        return parseCalendar(dateStr, DatePattern.NORM_DATE_PATTERN);
    }

    public static Calendar parseCalendar(String dateStr, String format) {
        Calendar cl = Calendar.getInstance();
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            cl.setTime(df.parse(dateStr));
        } catch (Exception e) {
            cl = null;
            e.printStackTrace();
        }
        return cl;
    }

    /**
     * 默认日期格式：yyyy-MM-dd
     */
    public static String parseString(Calendar cl) {
        return parseString(cl, DatePattern.NORM_DATE_PATTERN);
    }

    /**
     * 默认日期格式：yyyy-MM-dd HH:mm
     */
    public static String parseStringSEC(Calendar cl) {
        return parseString(cl, DatePattern.NORM_DATETIME_MINUTE_PATTERN);
    }

    public static String parseString(Calendar cl, String format) {
        String retStr = "";
        if (cl != null) {
            try {
                SimpleDateFormat df = new SimpleDateFormat(format);
                retStr = df.format(cl.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retStr;
    }

}