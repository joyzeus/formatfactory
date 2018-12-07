package com.jay.formatfactory.util;

import java.text.DecimalFormat;

public class FileSizeUtils {

    private static final int SIZE = 1024;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    private static final String[] UNITS = {"B", "KB", "MB", "GB"};

    public enum Unit {
        UNIT_BYTE(0),
        UNIT_KB(1),
        UNIT_MB(2),
        UNIT_GB(3);
        private int index;

        Unit(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    public static String formatAutoUnit(long data, Unit unit) {
        int index = unit.getIndex();
        return formatAutoUnit(data, index);
    }

    private static String formatAutoUnit(long data, int index) {
        if (index >= 0 && index < UNITS.length - 1) {
            long tmp = data / SIZE;
            if (tmp == 0) {
                return DECIMAL_FORMAT.format(data) + UNITS[index];
            } else {
                return formatAutoUnit(tmp, index + 1);
            }
        } else if (index == UNITS.length - 1) {
            return DECIMAL_FORMAT.format(data) + UNITS[index];
        } else {
            return "0.00B";
        }
    }
}