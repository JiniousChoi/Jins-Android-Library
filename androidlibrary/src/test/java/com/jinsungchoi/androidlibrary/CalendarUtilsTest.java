package com.jinsungchoi.androidlibrary;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by greenjin on 16. 6. 10.
 */
public class CalendarUtilsTest extends TestCase {

    public void testParse() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2016, 5, 1, 0, 0, 0); //month is zero based
        Date expected = cal.getTime();

        Date actual = CalendarUtils.parse("2016-06-01", CalendarUtils.yyyyMMdd);

        assertEquals(expected, actual);
    }

    public void testGetDate() throws Exception {
        Date expected = CalendarUtils.parse("2016-06-01", CalendarUtils.yyyyMMdd);

        Date actual = CalendarUtils.getDate(2016, 6, 1);

        assertEquals(expected, actual);
    }

    public void testGetToday() throws Exception {
        Date expected = new Date();
        int year0 = expected.getYear();
        int month0 = expected.getMonth();
        int date0 = expected.getDate();

        Date actual = CalendarUtils.getToday();
        int year1 = actual.getYear();
        int month1 = actual.getMonth();
        int date1 = actual.getDate();

        assertEquals(year0, year1);
        assertEquals(month0, month1);
        assertEquals(date0, date1);
    }

    public void testNextDay1() throws Exception {
        Date expected = CalendarUtils.getDate(2016, 1, 1);

        Date lastDayOf2015 = CalendarUtils.getDate(2015, 12, 31);
        Date actual = CalendarUtils.nextDay(lastDayOf2015);

        assertEquals(expected, actual);
    }

    public void testNextDay2() throws Exception {
        Date today = CalendarUtils.getDate(2016, 6, 7);

        Date yesterday = CalendarUtils.getDate(2016, 6, 6);
        Date nextDayOfYesterday = CalendarUtils.nextDay(yesterday);

        assertEquals(today, nextDayOfYesterday);
    }

}