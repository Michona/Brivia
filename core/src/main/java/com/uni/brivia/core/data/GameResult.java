package com.uni.brivia.core.data;

public class GameResult {

    private final Boolean mIsCorrect;

    private final Integer mPoints;

    private final Integer mBonusPoints;

    public GameResult(Boolean isCorrect, Integer points, Integer bonusPoints) {
        this.mIsCorrect = isCorrect;
        this.mPoints = points;
        this.mBonusPoints = bonusPoints;
    }

    public Boolean getIsCorrect() {
        return mIsCorrect;
    }

    public Integer getBonusPoints() {
        return mBonusPoints;
    }

    public Integer getPoints() {
        return mPoints;
    }
}
