package com.jinsungchoi.androidlibrary;

/**
 * Created by greenjin on 16. 8. 11.
 */
public class DebugUtils {

    /**
     * set only one time during the whole life time of a process
     */
    private static boolean sDebuggable = false;

    public static boolean isDebuggable() {
        return sDebuggable;
    }

    public static void setDebug(boolean debug) {
        sDebuggable = debug;
    }
}
