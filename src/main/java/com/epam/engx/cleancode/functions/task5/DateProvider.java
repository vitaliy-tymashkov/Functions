package com.epam.engx.cleancode.functions.task5;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {

    private static final int DAY_FORWARD = 1;
    private static final int DAY_BACKWARD = -1;
    private static final int ZERO = 0;

    public Date changeToMidnightForward(Date date) {
        Calendar calendar = getCalendar(date);
        calendar.add(Calendar.DATE, DAY_FORWARD);
        return getDate(calendar);
    }

    public Date changeToMidnightReverse(Date date) {
        Calendar calendar = getCalendar(date);
        calendar.add(Calendar.DATE, DAY_BACKWARD);
        return getDate(calendar);
    }

    private Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Date getDate(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, ZERO);
        calendar.set(Calendar.MINUTE, ZERO);
        calendar.set(Calendar.SECOND, ZERO);
        calendar.set(Calendar.MILLISECOND, ZERO);
        return calendar.getTime();
    }
}
