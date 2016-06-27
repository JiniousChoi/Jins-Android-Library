package com.jinsungchoi.androidlibrary;

/**
 * Created by greenjin on 16. 3. 26.
 */
public class TimerUtils {

    public static String transToTimerFormat(int seconds) {
        int SS = seconds % 60;
        int MM = (seconds / 60) % 60;
        int HH = seconds / 3600;

        return String.format("%02d:%02d:%02d", HH, MM, SS);
    }

    public static String transToTimerFormat(long millis) {
        int seconds = (int) (millis/1000);
        return transToTimerFormat(seconds);
    }
}
