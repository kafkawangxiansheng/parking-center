package net.spm.common.util;

public class NumberUtil {

    public static double round(double number, int scale) {
        if (scale < 0) {
            return number;
        } else {
            double base = Math.pow(10, scale);
            return Math.round(number * base) / base;
        }
    }
}
