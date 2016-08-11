package com.jinsungchoi.androidlibrary;

import com.google.android.gms.analytics.Tracker;
import com.jinsungchoi.androidlibrary.android.GoogleTracker;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by greenjin on 16. 6. 4.
 */
public class ExceptionUtils {
    private static final String LOG_TAG = ExceptionUtils.class.getSimpleName();

    public static String getStackTrace(Exception ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }

    public static void printAndReportStackTrace(Tracker tracker, Exception ex) {
        String trace = getStackTrace(ex);
        LogDev.e(LOG_TAG, trace);
        GoogleTracker.trackerHit(tracker, "error", trace, null, null);
    }

    public static void LogAndThrowExInDev(String tag, String errorMsg, Class<? extends RuntimeException> clazz) {
        if(!DebugUtils.isDebuggable()) return;

        //logging
        LogDev.e(tag, errorMsg);

        //throwing
        try {
            Constructor<?> cons = clazz.getConstructor(String.class);
            // 어차피 stack trace 메시지에는 runtimeException이 아니라 IllegalStateException등 본래
            // 예외명이 찍히게 되므로, 런타임으로 업케스팅해서 사용해도 무관함.
            RuntimeException re = (RuntimeException) cons.newInstance(errorMsg);
            throw re;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
