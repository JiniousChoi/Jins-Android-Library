package com.jinsungchoi.androidlibrary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by greenjin on 16. 3. 26.
 */
public class TimerUtilsTest {

    @Test
    public void testTransToTimerFormat_seconds() {
        String result = TimerUtils.transToTimerFormat(0);
        String expected = "00:00:00";
        assertEquals(expected, result);

        result = TimerUtils.transToTimerFormat(15);
        expected = "00:00:15";
        assertEquals(expected, result);

        result = TimerUtils.transToTimerFormat(59);
        expected = "00:00:59";
        assertEquals(expected, result);
    }

    @Test
    public void testTransToTimerFormat_minutes() {
        String result = TimerUtils.transToTimerFormat(60);
        String expected = "00:01:00";
        assertEquals(expected, result);

        result = TimerUtils.transToTimerFormat(61);
        expected = "00:01:01";
        assertEquals(expected, result);

        result = TimerUtils.transToTimerFormat(75);
        expected = "00:01:15";
        assertEquals(expected, result);

        result = TimerUtils.transToTimerFormat(119);
        expected = "00:01:59";
        assertEquals(expected, result);

        result = TimerUtils.transToTimerFormat(120);
        expected = "00:02:00";
        assertEquals(expected, result);

        result = TimerUtils.transToTimerFormat(55*60);
        expected = "00:55:00";
        assertEquals(expected, result);

        result = TimerUtils.transToTimerFormat(59*60+59);
        expected = "00:59:59";
        assertEquals(expected, result);
    }

    @Test
    public void testTransToTimerFormat_hours() {
        String result = TimerUtils.transToTimerFormat(3600);
        String expected = "01:00:00";
        assertEquals(expected, result);

        result = TimerUtils.transToTimerFormat(1*3600+0*60+1);
        expected = "01:00:01";
        assertEquals(expected, result);

        result = TimerUtils.transToTimerFormat(1*3600+55*60+55);
        expected = "01:55:55";
        assertEquals(expected, result);

        result = TimerUtils.transToTimerFormat(12*3600+34*60+56);
        expected = "12:34:56";
        assertEquals(expected, result);

        result = TimerUtils.transToTimerFormat(99*3600+59*60+59);
        expected = "99:59:59";
        assertEquals(expected, result);
    }

    @Test
    public void testTransToTimerFormat_exceed() {
        String result = TimerUtils.transToTimerFormat(99*3600 + 59*60 + 59 + 1);
        String expected = "100:00:00";
        assertEquals(expected, result);
    }
}