package com.jinsungchoi.androidlibrary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by greenjin on 16. 6. 9.
 */
public class CalendarUtils {

    public static String yyyyMMdd = "yyyy-MM-dd";

    public static String format(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     *
     * @param year
     * @param month is 1-based for this method
     * @param dayOfMonth
     * @return
     */
    public static Date getDate(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month-1, dayOfMonth); //month is 0-based
        return calendar.getTime();
    }

    public static Date parse(String strDate, String formatter) {
        SimpleDateFormat format = new SimpleDateFormat(formatter);
        Date date = null;

        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String stringToString(String strDate, String fromFmt, String toFmt) {
        Date date = parse(strDate, fromFmt);
        return format(date, toFmt);
    }

    public static List<String> stringsToString(List<String> dates, String fromFmt, String toFmt) {
        List<String> newDates = new ArrayList<>();
        for(String date : dates) {
            String newDate = stringToString(date, fromFmt, toFmt);
            newDates.add(newDate);
        }

        return newDates;
    }

    /**
     *
     * @return today at midnight
     */
    public static Date getToday() {
        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DATE);

        cal.clear();
        cal.set(year, month, dayOfMonth);
        return cal.getTime();
    }

    public static Date nextDay(Date idxDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(idxDate);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }
}
