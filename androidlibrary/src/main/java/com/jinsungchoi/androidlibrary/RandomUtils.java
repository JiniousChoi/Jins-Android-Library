package com.jinsungchoi.androidlibrary;

import java.util.Random;

/**
 * Created by greenjin on 16. 6. 9.
 */
public class RandomUtils {

    /**
     *
     * @param begin inclusive
     * @param end exclusive
     * @return
     */
    public static int range(int begin, int end) {
        Random random = new Random();
        int x = random.nextInt(end-begin);
        return x + begin;
    }
}
