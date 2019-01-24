package com.sky.gank.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * 类名称：DateUtil
 * 类功能：有关时间获取，时间转换的类
 * 类作者：20151002001
 * 类日期：2016-03-25.
 **/
public class DateUtil {
    /**
     * 获取当前时间
     */
    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        return df.format(System.currentTimeMillis());
    }

    /**
     * 获取当天日期 yyyy-MM-dd
     * @return df
     */
    public static String getCurrentToday() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        return df.format(System.currentTimeMillis());
    }

    public static String getYesterday(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return df.format(cal.getTime());
    }

    /**
     * 将yyyy-MM-dd 转化成 xx月xx日 (如 2017-08-10  转化后是 8月10日)
     * @param dateStr dateStr
     * @return data
     */
    public static String getDateFormat(String dateStr){
        String data = null;
        String[] dateArg=dateStr.split("-");
        if(2 < dateArg.length ){
            String month=dateArg[1].startsWith("0")?dateArg[1].substring(1):dateArg[1];
            String day=dateArg[2].startsWith("0")?dateArg[2].substring(1):dateArg[2];
            data = month+"月"+day+"日";
        }
        return data;
    }

    public static String formatDateStringToString(String date){
        if(date.length() < 10){
            return "";
        }
        return date.substring(0,10).replace('-','/');
    }

}
