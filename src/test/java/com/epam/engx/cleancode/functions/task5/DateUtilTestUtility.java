package com.epam.engx.cleancode.functions.task5;

import java.util.Date;

public class DateUtilTestUtility {

    private DateUtilTestUtility() {
        throw new UnsupportedOperationException();
    }

    public static Date getDirectlyIncrementedDate(DateProvider dateProvider, Date date) {
        return dateProvider.changeToMidnightForward(date);
    }

    public static Date getInverseIncrementedDate(DateProvider dateProvider, Date date) {
        return dateProvider.changeToMidnightReverse(date);
    }
}
