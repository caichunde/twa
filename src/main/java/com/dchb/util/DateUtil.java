package com.dchb.util;

import org.springframework.util.StringUtils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class DateUtil {
    public static String changeDateFormat(String dateStr, String sourceFormat, String targetFormat)
            throws Exception {
        Date date = stringToDate(dateStr, sourceFormat);
        return dateToString(date, targetFormat);
    }

    public static Date stringToDate(String dateString)
            throws Exception {
        Date vdate = null;
        String vformat = null;
        if (dateString == null) {
            return null;
        }
        if ((dateString.length() != 4) && (dateString.length() != 6) &&
                (dateString.length() != 7) && (dateString.length() != 8) &&
                (dateString.length() != 10) && (dateString.length() != 14) &&
                (dateString.length() != 19)) {
            throw new Exception("[时间串]输入格式错误,请输入合法的日期格式!");
        }
        if (dateString.length() == 4) {
            vformat = "yyyy";
        } else if (dateString.length() == 6) {
            vformat = "yyyyMM";
        } else if (dateString.length() == 7) {
            dateString =
                    dateString.substring(0, 4) + dateString.substring(5, 7);
            vformat = "yyyyMM";
        } else if (dateString.length() == 8) {
            vformat = "yyyyMMdd";
        } else if (dateString.length() == 10) {
            dateString =
                    dateString.substring(0, 4) + dateString.substring(5, 7) + dateString.substring(8, 10);
            vformat = "yyyyMMdd";
        } else if (dateString.length() == 14) {
            vformat = "yyyyMMddHHmmss";
        } else if (dateString.length() == 19) {
            vformat = "yyyy-MM-dd HH:mm:ss";
        }
        vdate = stringToDate(dateString, vformat);
        return vdate;
    }

    public static Date stringToDate(String dateString, String format)
            throws Exception {
        if (dateString == null) {
            return null;
        }
        if (dateString.equalsIgnoreCase("")) {
            throw new Exception("传入参数中的[时间串]为空");
        }
        if ((format == null) || (format.equalsIgnoreCase(""))) {
            throw new Exception("传入参数中的[时间格式]为空");
        }
        Hashtable<Integer, String> h = new Hashtable();
        String javaFormat = new String();
        if (format.indexOf("yyyy") != -1) {
            h.put(new Integer(format.indexOf("yyyy")), "yyyy");
        } else if (format.indexOf("yy") != -1) {
            h.put(new Integer(format.indexOf("yy")), "yy");
        }
        if (format.indexOf("MM") != -1) {
            h.put(new Integer(format.indexOf("MM")), "MM");
        } else if (format.indexOf("mm") != -1) {
            h.put(new Integer(format.indexOf("mm")), "MM");
        }
        if (format.indexOf("dd") != -1) {
            h.put(new Integer(format.indexOf("dd")), "dd");
        }
        if (format.indexOf("hh24") != -1) {
            h.put(new Integer(format.indexOf("hh24")), "HH");
        } else if (format.indexOf("hh") != -1) {
            h.put(new Integer(format.indexOf("hh")), "HH");
        } else if (format.indexOf("HH") != -1) {
            h.put(new Integer(format.indexOf("HH")), "HH");
        }
        if (format.indexOf("mi") != -1) {
            h.put(new Integer(format.indexOf("mi")), "mm");
        } else if ((format.indexOf("mm") != -1) && (h.containsValue("HH"))) {
            h.put(new Integer(format.lastIndexOf("mm")), "mm");
        }
        if (format.indexOf("ss") != -1) {
            h.put(new Integer(format.indexOf("ss")), "ss");
        }
        if (format.indexOf("SSS") != -1) {
            h.put(new Integer(format.indexOf("SSS")), "SSS");
        }
        for (int intStart = 0; format.indexOf("-", intStart) != -1; intStart++) {
            intStart = format.indexOf("-", intStart);
            h.put(new Integer(intStart), "-");
        }
        for (int intStart = 0; format.indexOf(".", intStart) != -1; intStart++) {
            intStart = format.indexOf(".", intStart);
            h.put(new Integer(intStart), ".");
        }
        for (int intStart = 0; format.indexOf("/", intStart) != -1; intStart++) {
            intStart = format.indexOf("/", intStart);
            h.put(new Integer(intStart), "/");
        }
        for (int intStart = 0; format.indexOf(" ", intStart) != -1; intStart++) {
            intStart = format.indexOf(" ", intStart);
            h.put(new Integer(intStart), " ");
        }
        for (int intStart = 0; format.indexOf(":", intStart) != -1; intStart++) {
            intStart = format.indexOf(":", intStart);
            h.put(new Integer(intStart), ":");
        }
        if (format.indexOf("年") != -1) {
            h.put(new Integer(format.indexOf("年")), "年");
        }
        if (format.indexOf("月") != -1) {
            h.put(new Integer(format.indexOf("月")), "月");
        }
        if (format.indexOf("日") != -1) {
            h.put(new Integer(format.indexOf("日")), "日");
        }
        if (format.indexOf("时") != -1) {
            h.put(new Integer(format.indexOf("时")), "时");
        }
        if (format.indexOf("分") != -1) {
            h.put(new Integer(format.indexOf("分")), "分");
        }
        if (format.indexOf("秒") != -1) {
            h.put(new Integer(format.indexOf("秒")), "秒");
        }
        int i = 0;
        while (h.size() != 0) {
            Enumeration<Integer> e = h.keys();
            int n = 0;
            while (e.hasMoreElements()) {
                i = ((Integer) e.nextElement()).intValue();
                if (i >= n) {
                    n = i;
                }
            }
            String temp = (String) h.get(new Integer(n));
            h.remove(new Integer(n));
            javaFormat = temp + javaFormat;
        }
        SimpleDateFormat df = new SimpleDateFormat(javaFormat);
        df.setLenient(false);
        Date myDate = new Date();
        try {
            myDate = df.parse(dateString);
        } catch (ParseException e) {
            throw new Exception("日期格式转换错误!将dateString转换成时间时出错");
        }
        return myDate;
    }

    public static String dateToString(Date date)
            throws Exception {
        return dateToString(date, "yyyyMMddHHmmss");
    }

    public static String dateToString(Date date, String format)
            throws Exception {
        if (date == null) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            throw new Exception("传入参数中的[时间格式]为空");
        }
        Hashtable<Integer, String> h = new Hashtable();
        String javaFormat = new String();
        if (format.indexOf("yyyy") != -1) {
            h.put(new Integer(format.indexOf("yyyy")), "yyyy");
        } else if (format.indexOf("yy") != -1) {
            h.put(new Integer(format.indexOf("yy")), "yy");
        }
        if (format.indexOf("MM") != -1) {
            h.put(new Integer(format.indexOf("MM")), "MM");
        } else if (format.indexOf("mm") != -1) {
            h.put(new Integer(format.indexOf("mm")), "MM");
        }
        if (format.indexOf("dd") != -1) {
            h.put(new Integer(format.indexOf("dd")), "dd");
        }
        if (format.indexOf("hh24") != -1) {
            h.put(new Integer(format.indexOf("hh24")), "HH");
        } else if (format.indexOf("hh") != -1) {
            h.put(new Integer(format.indexOf("hh")), "HH");
        } else if (format.indexOf("HH") != -1) {
            h.put(new Integer(format.indexOf("HH")), "HH");
        }
        if (format.indexOf("mi") != -1) {
            h.put(new Integer(format.indexOf("mi")), "mm");
        } else if ((format.indexOf("mm") != -1) && (h.containsValue("HH"))) {
            h.put(new Integer(format.lastIndexOf("mm")), "mm");
        }
        if (format.indexOf("ss") != -1) {
            h.put(new Integer(format.indexOf("ss")), "ss");
        }
        if (format.indexOf("SSS") != -1) {
            h.put(new Integer(format.indexOf("SSS")), "SSS");
        }
        for (int intStart = 0; format.indexOf("-", intStart) != -1; intStart++) {
            intStart = format.indexOf("-", intStart);
            h.put(new Integer(intStart), "-");
        }
        for (int intStart = 0; format.indexOf(".", intStart) != -1; intStart++) {
            intStart = format.indexOf(".", intStart);
            h.put(new Integer(intStart), ".");
        }
        for (int intStart = 0; format.indexOf("/", intStart) != -1; intStart++) {
            intStart = format.indexOf("/", intStart);
            h.put(new Integer(intStart), "/");
        }
        for (int intStart = 0; format.indexOf(" ", intStart) != -1; intStart++) {
            intStart = format.indexOf(" ", intStart);
            h.put(new Integer(intStart), " ");
        }
        for (int intStart = 0; format.indexOf(":", intStart) != -1; intStart++) {
            intStart = format.indexOf(":", intStart);
            h.put(new Integer(intStart), ":");
        }
        if (format.indexOf("年") != -1) {
            h.put(new Integer(format.indexOf("年")), "年");
        }
        if (format.indexOf("月") != -1) {
            h.put(new Integer(format.indexOf("月")), "月");
        }
        if (format.indexOf("日") != -1) {
            h.put(new Integer(format.indexOf("日")), "日");
        }
        if (format.indexOf("时") != -1) {
            h.put(new Integer(format.indexOf("时")), "时");
        }
        if (format.indexOf("分") != -1) {
            h.put(new Integer(format.indexOf("分")), "分");
        }
        if (format.indexOf("秒") != -1) {
            h.put(new Integer(format.indexOf("秒")), "秒");
        }
        int i = 0;
        while (h.size() != 0) {
            Enumeration<Integer> e = h.keys();
            int n = 0;
            while (e.hasMoreElements()) {
                i = ((Integer) e.nextElement()).intValue();
                if (i >= n) {
                    n = i;
                }
            }
            String temp = (String) h.get(new Integer(n));
            h.remove(new Integer(n));
            javaFormat = temp + javaFormat;
        }
        SimpleDateFormat df = new SimpleDateFormat(javaFormat, new DateFormatSymbols());
        return df.format(date);
    }

    public static String date2Week(Date date)
            throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int iWeek = calendar.get(7);
        String strWeek = "";
        if (1 == iWeek) {
            strWeek = "星期日";
        } else if (2 == iWeek) {
            strWeek = "星期一";
        } else if (3 == iWeek) {
            strWeek = "星期二";
        } else if (4 == iWeek) {
            strWeek = "星期三";
        } else if (5 == iWeek) {
            strWeek = "星期四";
        } else if (6 == iWeek) {
            strWeek = "星期五";
        } else if (7 == iWeek) {
            strWeek = "星期六";
        }
        return strWeek;
    }

    public static Date addDay(Date date, int dayNumber)
            throws Exception {
        if ((date == null) || (dayNumber == 0)) {
            return date;
        }
        Calendar vcal = Calendar.getInstance();
        vcal.setTime(date);
        vcal.add(5, dayNumber);
        date = vcal.getTime();
        return date;
    }

    public static String addDayToString(String dateString, String format, int dayNumber)
            throws Exception {
        Date vdate = stringToDate(dateString, format);
        vdate = addDay(vdate, dayNumber);
        String vdates = dateToString(vdate, format);
        return vdates;
    }

    public static long getDaysDiffBetweenTwoDate(Date beginDate, Date endDate)
            throws Exception {
        if (beginDate == null) {
            throw new Exception("传入参数[开始时间]为空");
        }
        if (endDate == null) {
            throw new Exception("传入参数[结束时间]为空");
        }
        if (beginDate.after(endDate)) {
            throw new Exception("传入参数[开始时间]晚于[结束时间]");
        }
        long ld1 = beginDate.getTime();
        long ld2 = endDate.getTime();
        long days = (ld2 - ld1) / 86400000L;
        return days;
    }

    public static String getMonthAgo(Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        Date date1 = c.getTime();
        return DateUtil.dateToString(date1, "yyyymmddhh24miss");
    }

}

