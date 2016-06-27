package com.jinsungchoi.androidlibrary;

import junit.framework.TestCase;

/**
 * Created by greenjin on 16. 6. 10.
 */
public class TimeUtilsTest extends TestCase {

    public void testMinToHr() throws Exception {
        assertMinToHr(30, "0.5");
        assertMinToHr(60, "1.0");
        assertMinToHr(90, "1.5");
    }

    private void assertMinToHr(int minutes, String formatted) {
        String actual = TimeUtils.minToHr(minutes);
        assertEquals(formatted, actual);
    }

    public void testMinToHrAndMin() throws Exception {
        assertMinToHrAndMin(0, "");
        assertMinToHrAndMin(1, "1 minute");
        assertMinToHrAndMin(30, "30 minutes");
        assertMinToHrAndMin(59, "59 minutes");

        assertMinToHrAndMin(60, "1 hour");
        assertMinToHrAndMin(61, "1 hour 1 minute");
        assertMinToHrAndMin(90, "1 hour 30 minutes");

        assertMinToHrAndMin(120, "2 hours");
        assertMinToHrAndMin(181, "3 hours 1 minute");
        assertMinToHrAndMin(200, "3 hours 20 minutes");
    }

    private void assertMinToHrAndMin(int minutes, String formatted) {
        String actual = TimeUtils.minToHrAndMin(minutes);

        assertEquals(formatted, actual);
    }
}