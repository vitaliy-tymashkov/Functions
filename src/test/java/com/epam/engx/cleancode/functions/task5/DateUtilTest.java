package com.epam.engx.cleancode.functions.task5;

import static com.epam.engx.cleancode.functions.task5.DateUtilTestUtility.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilTest {

    private static final int MONTH_SHIFT = -1;
    private static final String EXPECTED_DATE_11_OCT_2014 = "10/11/2014 00:00:00";
    private static final String EXPECTED_DATE_09_OCT_2014 = "10/09/2014 00:00:00";
    private static final String DATE_TIME_PATTERN = "MM/dd/yyyy HH:mm:ss";

    private static final int YEAR = 2014;
    private static final int MONTH = 10;
    private static final int DAY = 10;
    private final Date date = create(YEAR, MONTH, DAY);

    private final DateUtil dateUtil = new DateUtil();

    @Test
    public void shouldIncrementDate() {
        assertEquals(EXPECTED_DATE_11_OCT_2014, format(getDirectlyIncrementedDate(dateUtil, date)));
    }

    @Test
    public void shouldDecrementDate() {
        assertEquals(EXPECTED_DATE_09_OCT_2014, format(getInverseIncrementedDate(dateUtil, date)));
    }

    private String format(Date date) {
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(date);
    }

    public Date create(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month + MONTH_SHIFT);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }
}
