package com.epam.engx.cleancode.functions.task5;

import java.util.Date;

public class DateUtilTestUtility {

    private DateUtilTestUtility() {
        throw new UnsupportedOperationException();
    }

    public static Date getDirectlyIncrementedDate(DateUtil dateUtil, Date date) {
        return dateUtil.changeToMidnightForward(date);
    }

    public static Date getInverseIncrementedDate(DateUtil dateUtil, Date date) {
        return dateUtil.changeToMidnightReverse(date);
    }
}
