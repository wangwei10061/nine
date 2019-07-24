package com.ali.test.nine.util;

import java.text.DecimalFormat;

public class StringUtil {

    public static String twoPoint(double value) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(value);
    }
}
