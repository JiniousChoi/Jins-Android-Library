package com.jinsungchoi.androidlibrary;

/**
 * Created by greenjin on 16. 6. 10.
 */
public class TimeUtils {
    public static String minToHr(int minutes) {
        double hr = 1.0 * minutes / 60;
        return String.format("%.1f", hr);
    }

    public static String minToHrAndMin(int averageStudiedMins) {
        int hr = averageStudiedMins / 60;
        int min = averageStudiedMins % 60;

        StringBuilder sb = new StringBuilder();

        if(hr>0) {
            sb.append(hr);
            sb.append(" ");
            sb.append(hr>1? "hours" : "hour"); //hours vs hour
        }

        if(hr>0 && min>0) {
            sb.append(" ");
        }

        if(min>0) {
            sb.append(min);
            sb.append(" ");
            sb.append(min>1 ? "minutes" : "minute"); //minutes vs minute
        }

        return sb.toString();
    }
}
