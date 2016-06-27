package com.jinsungchoi.androidlibrary;

/**
 * Created by greenjin on 16. 6. 26.
 */
public class IntegerUtils {
    public static boolean isSameValue(Integer i1, Integer i2) {
        if(i1==null || i2==null) return false;

        return i1.equals(i2);
    }
}
