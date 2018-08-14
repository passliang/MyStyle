package com.zgqb.loan.app.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by schongking on 2017/7/10.
 */
public class DateUtil {

    /**
     * 获取当前时间
     * @return
     */
    public static Long getNowTime(){
        return System.currentTimeMillis();
    }

    public static String getMillisStr(Long Millis,String formate){
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        return sdf.format(Millis);
    }

    /**
     * 格式化时间
     * @param time
     * @return
     */
    public static String getDate(Long time){
        String date =null;
        if(time != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.format(Long.valueOf(time));
        }
        return date;
    }

    /**
     * 格式化购买时间
     * @param time
     * @return
     */
    public static String getBuyDate(Long time){
        String date =null;
        if(time != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            date = sdf.format(Long.valueOf(time));
        }
        return date;
    }

    /**
     * 将字符串转为时间戳
     *
     * @param user_time
     * @return
     */
    public static String dateToTimestamp(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    /**
     * 将yyyy-MM-dd格式的时间字符串转为时间戳
     *
     * @param user_time
     * @return
     */
    public static long endDateToLong(String user_time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date d;
        long l=0l;
        try {
            d = sdf.parse(user_time);
            l = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 将yyyy-MM-dd格式的时间字符串转为时间戳
     *
     * @param user_time
     * @return
     */
    public static long startDateToLong(String user_time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date d;
        long l=0l;
        try {
            d = sdf.parse(user_time);
            l = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 将字符串转换成日期格式
     * @throws ParseException
     */
    public static Date stringToDate(String user_time) {
        try {
            String year = user_time.substring(0, 4);
            String month = user_time.substring(4, 6);
            String day = user_time.substring(6, 8);

            String time = year+"-"+month+"-"+day;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date date = sdf.parse(time);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用户
     * @throws ParseException
     */
    public static long timeToNow(Long addTime) {

        if(addTime == null){
            return 0;
        }
        long nowTime = System.currentTimeMillis();
        long flag = nowTime-addTime;

        return flag;
    }


}
