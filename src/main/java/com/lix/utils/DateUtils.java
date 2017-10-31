package com.lix.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 返回指定格式日期(日期->字符串)
     * 
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 返回指定格式的日期(字符串->日期)
     * 
     * @param date
     * @param format
     * @return
     */
    public static Date strToDate(String date, String format) {
        Date formatDate;
        try {
            formatDate = new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return formatDate;
    }

    /**
     * 返回指定格式的当前系统日期
     * 
     * @param format
     * @return
     */
    public static String NowDateToStr(String format) {

        return new SimpleDateFormat(format).format(new Date());
    }

    public static void main(String[] args) {
        // String str = DateUtils.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss");
        Date str = DateUtils.strToDate("2017-03-28 17:24:21", "yyyy-MM-dd hh:mm:ss");
        System.out.println(DateUtils.NowDateToStr("yyyy-MM-dd HH:mm:ss"));
    }

}
