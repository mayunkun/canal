/**
 * Copyright 2018 众链科技 http://www.pchain.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.cn.canal.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * 日期处理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;
    private static final long ONE_YEAR = 31536000000L;
 
    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
 // ~ Static fields/initializers  
    // =============================================  
  
    private static Logger logger = Logger.getLogger(DateUtils.class);  
    private static String defaultDatePattern = null;  
    private static String timePattern = "HH:mm";    
    public static final String TS_FORMAT = DateUtils.getDatePattern() + " HH:mm:ss.S";  
    /** 日期格式yyyy-MM字符串常量 */  
    private static final String MONTH_FORMAT = "yyyy-MM";  
    /** 日期格式yyyy-MM-dd字符串常量 */  
    private static final String DATE_FORMAT = "yyyy-MM-dd";  
    /** 日期格式HH:mm:ss字符串常量 */  
    private static final String HOUR_FORMAT = "HH:mm:ss";  
    /** 日期格式yyyy-MM-dd HH:mm:ss字符串常量 */  
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";  
    /** 某天开始时分秒字符串常量  00:00:00 */  
    private static final String DAY_BEGIN_STRING_HHMMSS = " 00:00:00";  
    /**  某天结束时分秒字符串常量  23:59:59  */  
    public static final String DAY_END_STRING_HHMMSS = " 23:59:59";  
    private static final String SHORT_DATE_FORMAT = "yyyyMMdd";
    
    private static SimpleDateFormat sdf_date_format = new SimpleDateFormat(DATE_FORMAT);  
    private static SimpleDateFormat sdf_hour_format = new SimpleDateFormat(HOUR_FORMAT);  
    private static SimpleDateFormat sdf_datetime_format = new SimpleDateFormat(DATETIME_FORMAT);  
    private static SimpleDateFormat short_date_format = new SimpleDateFormat(SHORT_DATE_FORMAT);
  
    // ~ Methods  
    // ================================================================  
  
    public DateUtils() {  
    }  
    
    public static Date getNowDate(){
    	return new Date();
    }
  
    /** 
     * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static Date getCurrentDateTime() {  
        try {  
            return getNowDate();  
        } catch (Exception e) {  
            logger.debug("DateUtil.getDateTime():" + e.getMessage());  
            return new Date();  
        }  
    }  
    
    /** 
     * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getDateTime() {  
        try {  
            return sdf_datetime_format.format(getNowDate());  
        } catch (Exception e) {  
            logger.debug("DateUtil.getDateTime():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 获得服务器当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getDate() {  
        try {  
            return sdf_date_format.format(getNowDate());  
        } catch (Exception e) {  
            logger.debug("DateUtil.getDate():" + e.getMessage());  
            return "";  
        }  
    }  
    
    /** 
     * 获得服务器当前日期，以格式为：yyyyddmm的日期字符串形式返回 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getShortDate() {  
        try {  
            return short_date_format.format(getNowDate());  
        } catch (Exception e) {  
            logger.debug("DateUtil.getShortDate():" + e.getMessage());  
            return "";  
        }  
    } 
    
    /** 
     * 获得服务器当前时间，以格式为：HH:mm:ss的日期字符串形式返回 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getTime() {  
        String temp = " ";  
        try {  
            temp += sdf_hour_format.format(getNowDate());  
            return temp;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getTime():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 统计时开始日期的默认值 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getStartDate() {  
        try {  
            return getYear() + "-01-01";  
        } catch (Exception e) {  
            logger.debug("DateUtil.getStartDate():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 统计时结束日期的默认值 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getEndDate() {  
        try {  
            return getDate();  
        } catch (Exception e) {  
            logger.debug("DateUtil.getEndDate():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 获得服务器当前日期的年份 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getYear() {  
        try {  
            return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));  
        } catch (Exception e) {  
            logger.debug("DateUtil.getYear():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 获得服务器当前日期的月份 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getMonth() {  
        try {  
            java.text.DecimalFormat df = new java.text.DecimalFormat();  
            df.applyPattern("00;00");  
            return df.format((Calendar.getInstance().get(Calendar.MONTH) + 1));  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMonth():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 获得服务器在当前月中天数 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getDay() {  
        try {  
            return String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));  
        } catch (Exception e) {  
            logger.debug("DateUtil.getDay():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 比较两个日期相差的天数 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int getMargin(String date1, String date2) {  
        int margin;  
        try {  
            ParsePosition pos = new ParsePosition(0);  
            ParsePosition pos1 = new ParsePosition(0);  
            Date dt1 = sdf_date_format.parse(date1, pos);  
            Date dt2 = sdf_date_format.parse(date2, pos1);  
            long l = dt1.getTime() - dt2.getTime();  
            margin = (int) (l / (24 * 60 * 60 * 1000));  
            return margin;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMargin():" + e.toString());  
            return 0;  
        }  
    }  
  
    /** 
     * 比较两个日期相差的天数 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static double getDoubleMargin(String date1, String date2) {  
        double margin;  
        try {  
            ParsePosition pos = new ParsePosition(0);  
            ParsePosition pos1 = new ParsePosition(0);  
            Date dt1 = sdf_datetime_format.parse(date1, pos);  
            Date dt2 = sdf_datetime_format.parse(date2, pos1);  
            long l = dt1.getTime() - dt2.getTime();  
            margin = (l / (24 * 60 * 60 * 1000.00));  
            return margin;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMargin():" + e.toString());  
            return 0;  
        }  
    }  
  
    /** 
     * 比较两个日期相差的月数 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int getMonthMargin(String date1, String date2) {  
        int margin;  
        try {  
            margin = (Integer.parseInt(date2.substring(0, 4)) - Integer.parseInt(date1.substring(0, 4))) * 12;  
            margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0",  
                    "-")) - Integer.parseInt(date1.substring(4, 7).replaceAll("-0", "-")));  
            return margin;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMargin():" + e.toString());  
            return 0;  
        }  
    }  
  
    /** 
     * 返回日期加X天后的日期 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date 
     * @param i 
     * @return 
     */  
    public static String addDay(String date, int i) {  
        try {  
            GregorianCalendar gCal = new GregorianCalendar(  
                    Integer.parseInt(date.substring(0, 4)),   
                    Integer.parseInt(date.substring(5, 7)) - 1,   
                    Integer.parseInt(date.substring(8, 10)));  
            gCal.add(GregorianCalendar.DATE, i);  
            return sdf_date_format.format(gCal.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.addDay():" + e.toString());  
            return getDate();  
        }  
    }  
  
    /** 
     * 返回日期加X月后的日期 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date 
     * @param i 
     * @return 
     */  
    public static String addMonth(String date, int i) {  
        try {  
            GregorianCalendar gCal = new GregorianCalendar(  
                    Integer.parseInt(date.substring(0, 4)),   
                    Integer.parseInt(date.substring(5, 7)) - 1,   
                    Integer.parseInt(date.substring(8, 10)));  
            gCal.add(GregorianCalendar.MONTH, i);  
            return sdf_date_format.format(gCal.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.addMonth():" + e.toString());  
            return getDate();  
        }  
    }  
  
    /** 
     * 返回日期加X年后的日期 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date 
     * @param i 
     * @return 
     */  
    public static String addYear(String date, int i) {  
        try {  
            GregorianCalendar gCal = new GregorianCalendar(  
                    Integer.parseInt(date.substring(0, 4)),   
                    Integer.parseInt(date.substring(5, 7)) - 1,   
                    Integer.parseInt(date.substring(8, 10)));  
            gCal.add(GregorianCalendar.YEAR, i);  
            return sdf_date_format.format(gCal.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.addYear():" + e.toString());  
            return "";  
        }  
    }  
  
    /** 
     * 返回某年某月中的最大天 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param year 
     * @param month 
     * @return 
     */  
    public static int getMaxDay(int iyear, int imonth) {  
        int day = 0;  
        try {  
            if (imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7  
                    || imonth == 8 || imonth == 10 || imonth == 12) {  
                day = 31;  
            } else if (imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11) {  
                day = 30;  
            } else if ((0 == (iyear % 4)) && (0 != (iyear % 100)) || (0 == (iyear % 400))) {  
                day = 29;  
            } else {  
                day = 28;  
            }  
            return day;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMonthDay():" + e.toString());  
            return 1;  
        }  
    }  
  
    /** 
     * 格式化日期 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param orgDate 
     * @param Type 
     * @param Span 
     * @return 
     */  
    @SuppressWarnings("static-access")  
    public String rollDate(String orgDate, int Type, int Span) {  
        try {  
            String temp = "";  
            int iyear, imonth, iday;  
            int iPos = 0;  
            char seperater = '-';  
            if (orgDate == null || orgDate.length() < 6) {  
                return "";  
            }  
  
            iPos = orgDate.indexOf(seperater);  
            if (iPos > 0) {  
                iyear = Integer.parseInt(orgDate.substring(0, iPos));  
                temp = orgDate.substring(iPos + 1);  
            } else {  
                iyear = Integer.parseInt(orgDate.substring(0, 4));  
                temp = orgDate.substring(4);  
            }  
  
            iPos = temp.indexOf(seperater);  
            if (iPos > 0) {  
                imonth = Integer.parseInt(temp.substring(0, iPos));  
                temp = temp.substring(iPos + 1);  
            } else {  
                imonth = Integer.parseInt(temp.substring(0, 2));  
                temp = temp.substring(2);  
            }  
  
            imonth--;  
            if (imonth < 0 || imonth > 11) {  
                imonth = 0;  
            }  
  
            iday = Integer.parseInt(temp);  
            if (iday < 1 || iday > 31)  
                iday = 1;  
  
            Calendar orgcale = Calendar.getInstance();  
            orgcale.set(iyear, imonth, iday);  
            temp = this.rollDate(orgcale, Type, Span);  
            return temp;  
        } catch (Exception e) {  
            return "";  
        }  
    }  
  
    public static String rollDate(Calendar cal, int Type, int Span) {  
        try {  
            String temp = "";  
            Calendar rolcale;  
            rolcale = cal;  
            rolcale.add(Type, Span);  
            temp = sdf_date_format.format(rolcale.getTime());  
            return temp;  
        } catch (Exception e) {  
            return "";  
        }  
    }  
  
    /** 
     * 返回默认的日期格式 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static synchronized String getDatePattern() {  
        defaultDatePattern = "yyyy-MM-dd";  
        return defaultDatePattern;  
    }  
  
    /** 
     * 将指定日期按默认格式进行格式代化成字符串后输出如：yyyy-MM-dd 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param aDate 
     * @return 
     */  
    public static final String getDate(Date aDate) {  
        SimpleDateFormat df = null;  
        String returnValue = "";  
        if (aDate != null) {  
            df = new SimpleDateFormat(getDatePattern());  
            returnValue = df.format(aDate);  
        }  
        return (returnValue);  
    }  
  
    /** 
     * 取得给定日期的时间字符串，格式为当前默认时间格式 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param theTime 
     * @return 
     */  
    public static String getTimeNow(Date theTime) {  
        return getDateTime(timePattern, theTime);  
    }  
  
    /** 
     * 取得当前时间的Calendar日历对象 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     * @throws ParseException 
     */  
    public Calendar getToday() throws ParseException {  
        Date today = new Date();  
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());  
        String todayAsString = df.format(today);  
        Calendar cal = new GregorianCalendar();  
        cal.setTime(convertStringToDate(todayAsString));  
        return cal;  
    }  
  
    /** 
     * 将日期类转换成指定格式的字符串形式 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param aMask 
     * @param aDate 
     * @return 
     */  
    public static final String getDateTime(String aMask, Date aDate) {  
        SimpleDateFormat df = null;  
        String returnValue = "";  
  
        if (aDate == null) {  
            logger.error("aDate is null!");  
        } else {  
            df = new SimpleDateFormat(aMask);  
            returnValue = df.format(aDate);  
        }  
        return (returnValue);  
    }  
  
    /** 
     * 将指定的日期转换成默认格式的字符串形式 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param aDate 
     * @return 
     */  
    public static final String convertDateToString(Date aDate) {  
        return getDateTime(getDatePattern(), aDate);  
    }  
  
    /** 
     * 将日期字符串按指定格式转换成日期类型 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param aMask 指定的日期格式，如:yyyy-MM-dd 
     * @param strDate 待转换的日期字符串 
     * @return 
     * @throws ParseException 
     */  
    public static final Date convertStringToDate(String aMask, String strDate)  
            throws ParseException {  
        SimpleDateFormat df = null;  
        Date date = null;  
        df = new SimpleDateFormat(aMask);  
  
        if (logger.isDebugEnabled()) {  
            logger.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");  
        }  
        try {  
            date = df.parse(strDate);  
        } catch (ParseException pe) {  
            logger.error("ParseException: " + pe);  
            throw pe;  
        }  
        return (date);  
    }  
  
    /** 
     * 将日期字符串按默认格式转换成日期类型 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param strDate 
     * @return 
     * @throws ParseException 
     */  
    public static Date convertStringToDate(String strDate)  
            throws ParseException {  
        Date aDate = null;  
  
        try {  
            if (logger.isDebugEnabled()) {  
                logger.debug("converting date with pattern: " + getDatePattern());  
            }  
            aDate = convertStringToDate(getDatePattern(), strDate);  
        } catch (ParseException pe) {  
            logger.error("Could not convert '" + strDate + "' to a date, throwing exception");  
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());  
        }  
        return aDate;  
    }  
  
    /** 
     * 返回一个JAVA简单类型的日期字符串 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getSimpleDateFormat() {  
        SimpleDateFormat formatter = new SimpleDateFormat();  
        String NDateTime = formatter.format(new Date());  
        return NDateTime;  
    }  
      
    /** 
     * 将指定字符串格式的日期与当前时间比较 
     * @author DYLAN 
     * @date Feb 17, 2012 
     * @param strDate 需要比较时间 
     * @return  
     *      <p> 
     *      int code 
     *      <ul> 
     *      <li>-1 当前时间 < 比较时间 </li> 
     *      <li> 0 当前时间 = 比较时间 </li> 
     *      <li>>=1当前时间 > 比较时间 </li> 
     *      </ul> 
     *      </p> 
     */  
    public static int compareToCurTime (String strDate) {  
        if (StringUtils.isBlank(strDate)) {  
            return -1;  
        }  
        Date curTime = getNowDate();  
        String strCurTime = null;  
        try {  
            strCurTime = sdf_datetime_format.format(curTime);  
        } catch (Exception e) {  
            if (logger.isDebugEnabled()) {  
                logger.debug("[Could not format '" + strDate + "' to a date, throwing exception:" + e.getLocalizedMessage() + "]");  
            }  
        }  
        if (StringUtils.isNotBlank(strCurTime)) {  
            return strCurTime.compareTo(strDate);  
        }  
        return -1;  
    }  
      
    /** 
     * 为查询日期添加最小时间 
     *  
     * @param 目标类型Date 
     * @param 转换参数Date 
     * @return 
     */  
    @SuppressWarnings("deprecation")  
    public static Date addStartTime(Date param) {  
        Date date = param;  
        try {  
            date.setHours(0);  
            date.setMinutes(0);  
            date.setSeconds(0);  
            return date;  
        } catch (Exception ex) {  
            return date;  
        }  
    }  
  
    /** 
     * 为查询日期添加最大时间 
     *  
     * @param 目标类型Date 
     * @param 转换参数Date 
     * @return 
     */  
    @SuppressWarnings("deprecation")  
    public static Date addEndTime(Date param) {  
        Date date = param;  
        try {  
            date.setHours(23);  
            date.setMinutes(59);  
            date.setSeconds(0);  
            return date;  
        } catch (Exception ex) {  
            return date;  
        }  
    }  
  
    /** 
     * 返回系统现在年份中指定月份的天数 
     *  
     * @param 月份month 
     * @return 指定月的总天数 
     */  
    @SuppressWarnings("deprecation")  
    public static String getMonthLastDay(int month) {  
        Date date = new Date();  
        int[][] day = { { 0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },  
                { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };  
        int year = date.getYear() + 1900;  
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {  
            return day[1][month] + "";  
        } else {  
            return day[0][month] + "";  
        }  
    }  
  
    /** 
     * 返回指定年份中指定月份的天数 
     *  
     * @param 年份year 
     * @param 月份month 
     * @return 指定月的总天数 
     */  
    public static String getMonthLastDay(int year, int month) {  
        int[][] day = { { 0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },  
                { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };  
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {  
            return day[1][month] + "";  
        } else {  
            return day[0][month] + "";  
        }  
    }  
  
    /** 
     * 判断是平年还是闰年 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param year 
     * @return 
     */   
    public static boolean isLeapyear(int year) {  
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400) == 0) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
  
    /** 
     * 取得当前时间的日戳 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    @SuppressWarnings("deprecation")  
    public static String getTimestamp() {  
        Date date = getNowDate();  
        String timestamp = "" + (date.getYear() + 1900) + date.getMonth()  
                + date.getDate() + date.getMinutes() + date.getSeconds()  
                + date.getTime();  
        return timestamp;  
    }  
  
    /** 
     * 取得指定时间的日戳 
     *  
     * @return 
     */  
    @SuppressWarnings("deprecation")  
    public static String getTimestamp(Date date) {  
        String timestamp = "" + (date.getYear() + 1900) + date.getMonth()  
                + date.getDate() + date.getMinutes() + date.getSeconds()  
                + date.getTime();  
        return timestamp;  
    }  
    
    public static String leavingFormmate(Date date) throws ParseException {
//    	 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	 long delta = new Date().getTime() - date.getTime();
    	 if (delta >= 1L * ONE_YEAR) {
    		 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
             return format.format(date);
         } else if(delta < 1L * ONE_YEAR && delta >= 2L * ONE_DAY) {
        	 SimpleDateFormat format =  new SimpleDateFormat("MM-dd"); 
             return format.format(date);
         } else if(delta >= 12L * ONE_HOUR && delta == 1L * ONE_DAY) {
        	 SimpleDateFormat format =  new SimpleDateFormat("HH:mm"); 
             return "昨天 "+format.format(date);
         } else if(delta >= 12L * ONE_HOUR && delta < 1L * ONE_DAY) {
        	 SimpleDateFormat format =  new SimpleDateFormat("HH:mm"); 
             return format.format(date);
         } else if(delta >= 1L * ONE_HOUR && delta < 12L * ONE_HOUR) {
        	 long hours = toHours(delta);
             return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
         } else if(delta < 1L * ONE_HOUR) {
             long minutes = toMinutes(delta);
             return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
         } else {
        	 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        	 return format.format(date);
         }
/*         if (delta < 1L * ONE_MINUTE) {
             long seconds = toSeconds(delta);
             return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
         }
         if (delta < 45L * ONE_MINUTE) {
             long minutes = toMinutes(delta);
             return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
         }
         if (delta < 24L * ONE_HOUR) {
             long hours = toHours(delta);
             return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
         }
         if (delta < 48L * ONE_HOUR) {
             return "昨天";
         }
         if (delta < 30L * ONE_DAY) {
             long days = toDays(delta);
             return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
         }
         if (delta < 12L * 4L * ONE_WEEK) {
             long months = toMonths(delta);
             return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
         } else {
             long years = toYears(delta);
             return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
         }*/
    }

    /**
     * 增加日期
     * 如 addDate(new Date(), 3) 指三天之后的现在
     * @param date
     * @param addNumber
     * @return
     */
    public static Date addDate(Date date, int addNumber){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.DAY_OF_YEAR, addNumber);
		return cal.getTime();
    }
    
	private static long toSeconds(long date) {
	    return date / 1000L;
	}
	
	private static long toMinutes(long date) {
	    return toSeconds(date) / 60L;
	}
	
	private static long toHours(long date) {
	    return toMinutes(date) / 60L;
	}
	
	private static long toDays(long date) {
	    return toHours(date) / 24L;
	}
	
	private static long toMonths(long date) {
	    return toDays(date) / 30L;
	}
	
	private static long toYears(long date) {
	    return toMonths(date) / 365L;
	}
  
    public static void main(String[] args) throws ParseException {  
//        System.out.println(getYear() + "|" + getMonth() + "|" + getDate());
/*    	String str = "2018-07-12 17:15:12";
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Date date = format.parse(str);
    	System.out.println(leavingFormmate(date));*/
    	System.out.println(addDate(new Date(), 300));
    }  
}
