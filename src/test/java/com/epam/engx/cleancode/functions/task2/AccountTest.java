package com.epam.engx.cleancode.functions.task2;

import com.epam.engx.cleancode.functions.task2.stubs.AccountStub;
import com.epam.engx.cleancode.functions.task2.stubs.NotRegisteredAccountStub;
import com.epam.engx.cleancode.functions.task2.stubs.NotVisitingAccountStub;
import com.epam.engx.cleancode.functions.task2.stubs.ReviewStub;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Level;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.NotActiveAccountException;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Review;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private static final int REVIEWS_TEN = 10;
    private static final int REVIEWS_THIRTY = 30;
    private static final String LEVEL_ONE_NUMBER = "1";
    private static final String LEVEL_TWO_NUMBER = "2";
    private static final Level LEVEL_ONE = new Level(LEVEL_ONE_NUMBER);
    private static final Level LEVEL_TWO = new Level(LEVEL_TWO_NUMBER);
    private static final Level LEVEL_DEFAULT = Level.defaultLevel();
    private static final ReviewStub REVIEW_STUB_ZERO = new ReviewStub(0);
    private static final ReviewStub REVIEW_STUB_NINE = new ReviewStub(9);
    private static final ReviewStub REVIEW_STUB_TEN = new ReviewStub(10);
    private static final ReviewStub REVIEW_STUB_30 = new ReviewStub(30);
    private static final ReviewStub REVIEW_STUB_5 = new ReviewStub(5);

    private TreeMap<Integer, Level> levelMap = new TreeMap<>(Collections.reverseOrder());

    @Before
    public void setUp() throws Exception {
        levelMap.put(REVIEWS_TEN, LEVEL_ONE);
        levelMap.put(REVIEWS_THIRTY, new Level(LEVEL_TWO_NUMBER));
    }

    @Test(expected = NotActiveAccountException.class)
    public void shouldThrowExceptionWhenAccountIsNotRegistered() {
        Account accountStub = new NotRegisteredAccountStub();
        accountStub.getActivityLevel();
    }

    @Test(expected = NotActiveAccountException.class)
    public void shouldThrowExceptionWhenAccountIsNotVisiting() {
        Account accountStub = new NotVisitingAccountStub();
        accountStub.getActivityLevel();
    }

    @Test
    public void shouldGetDefaultLevelWhenThereAreNoAnswers() {
        Account accountStub = new AccountStub(Arrays.<Review>asList(REVIEW_STUB_ZERO));
        assertEquals(LEVEL_DEFAULT, accountStub.getActivityLevel());
    }

    @Test
    public void shouldGetDefaultLevelWhenThereAreNotEnoughAnswers() {
        Account accountStub = new AccountStub(Arrays.<Review>asList(REVIEW_STUB_NINE));
        assertEquals(LEVEL_DEFAULT, accountStub.getActivityLevel());
    }

    @Test
    public void shouldGetFirstLevelWhenThereAreEnoughAnswers() {
        Account accountStub = new AccountStub(Arrays.<Review>asList(REVIEW_STUB_TEN));
        accountStub.setThresholdToLevel(levelMap);
        assertEquals(LEVEL_ONE, accountStub.getActivityLevel());
    }

    @Test
    public void shouldGetFirstLevelWhenThereAreEnoughSumOfAnswers() {
        Account accountStub = new AccountStub(Arrays.<Review>asList(REVIEW_STUB_5, REVIEW_STUB_5));
        accountStub.setThresholdToLevel(levelMap);
        assertEquals(LEVEL_ONE, accountStub.getActivityLevel());
    }

    @Test
    public void shouldGetSecondLevelWhenThereAreEnoughAnswers() {
        Account accountStub = new AccountStub(Arrays.<Review>asList(REVIEW_STUB_30));
        accountStub.setThresholdToLevel(levelMap);
        assertEquals(LEVEL_TWO, accountStub.getActivityLevel());
    }
}
