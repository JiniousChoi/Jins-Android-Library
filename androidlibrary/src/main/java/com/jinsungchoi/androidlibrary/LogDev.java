package com.jinsungchoi.androidlibrary;

import android.util.Log;


/**
 * Created by greenjin on 16. 4. 16.
 */
public class LogDev {

    public static void v(String tag, String msg) {
        if(DebugUtils.isDebuggable()) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if(DebugUtils.isDebuggable()) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if(DebugUtils.isDebuggable()) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if(DebugUtils.isDebuggable()) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if(DebugUtils.isDebuggable()) {
            Log.e(tag, msg);
        }
    }
}
