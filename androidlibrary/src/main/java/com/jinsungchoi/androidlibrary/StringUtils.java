package com.jinsungchoi.androidlibrary;

import java.security.InvalidParameterException;

/**
 * Created by greenjin on 16. 1. 29.
 */
public class StringUtils {
    /**
     * @param strings
     * @param joiner
     */
    public static String join(String[] strings, String joiner) {
        StringBuilder result = new StringBuilder();
        int size = strings.length;
        int i;
        for (i = 0; i < size - 1; i++) {
            result.append(strings[i]);
            result.append(joiner);
        }
        result.append(strings[i]);

        return result.toString();
    }

    public static boolean isEmpty(String s) {
        if (s == null || (s.equals(""))) {
            return true;
        }

        return false;
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    //todo: 더 이쁘게 바꿀 순 없나?
    public static boolean isSame(String str1, String str2) {
        if (isEmpty(str1)) {
            if (isEmpty(str2)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (isEmpty(str2)) {
                return false;
            } else if (str1.equals(str2)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * This returns "" at least.
     * Never does it return null.
     *
     * @param str
     * @return
     */
    public static String assureNonNull(String str) {
        if (isEmpty(str)) {
            return "";
        } else {
            return str;
        }

    }

    public static int compareCaseInsensitiveAscendingOrder(String str1, String str2) {
        int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
        if (res == 0) {
            res = str1.compareTo(str2);
        }
        return res;
    }

    public static String format(String rawSql, String... args) {
        int argsLength = args.length;
        if(argsLength % 2 != 0) {
            throw new InvalidParameterException("length of args should be even");
        }

        for(int i=0; i<argsLength ; i=i+2) {
            String replacee = args[i];
            String replacer = args[i+1];

            rawSql = rawSql.replace(replacee, replacer);
        }

        return rawSql;
    }
}
