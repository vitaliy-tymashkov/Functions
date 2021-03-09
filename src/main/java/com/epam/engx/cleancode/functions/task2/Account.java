package com.epam.engx.cleancode.functions.task2;

import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Level;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.NotActiveAccountException;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Review;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.User;

import java.util.TreeMap;

public abstract class Account implements User {

    public static final int ZERO = 0;

    private TreeMap<Integer, Level> levelMap = new TreeMap<>();

    public Level getActivityLevel() {
        validateAccountForLevel();

        return getLevelByReviews(getReviewAnswers());
    }

    private int getReviewAnswers() {
        int result = 0;
        for (Review review : getAllReviews()) {
            result += review.getAnswers().size();
        }
        return result;
    }

    private void validateAccountForLevel() {
        if (isNotActiveAccount())
            throw new NotActiveAccountException();
    }

    private boolean isNotActiveAccount() {
        return isNotRegistered() || areVisitsZeroOrLess();
    }

    private boolean areVisitsZeroOrLess() {
        return getVisitNumber() <= ZERO;
    }

    private boolean isNotRegistered() {
        return !isRegistered();
    }

    private Level getLevelByReviews(int reviewAnswers) {
        for (Integer threshold : levelMap.keySet()) {
            if (isReviewAnswersAboveThreshold(reviewAnswers, threshold)) {
                return levelMap.get(threshold);
            }
        }

        return Level.defaultLevel();
    }

    private boolean isReviewAnswersAboveThreshold(int reviewAnswers, Integer threshold) {
        return reviewAnswers >= threshold;
    }

    public void setLevelMap(TreeMap<Integer, Level> levelMap) {
        this.levelMap = levelMap;
    }
}
