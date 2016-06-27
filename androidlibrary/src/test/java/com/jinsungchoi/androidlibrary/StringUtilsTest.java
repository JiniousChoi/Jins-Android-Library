package com.jinsungchoi.androidlibrary;

import junit.framework.TestCase;

/**
 * Created by greenjin on 16. 6. 9.
 */
public class StringUtilsTest extends TestCase {

    public void testFormat() throws Exception {

        String template = "{greeting} Jinsung {bying} {greeting}";
        String replaced = StringUtils.format(template,
                "{greeting}", "Good Morning",
                "{bying}", "Byebye");

        assertEquals("Good Morning Jinsung Byebye Good Morning", replaced);
    }
}