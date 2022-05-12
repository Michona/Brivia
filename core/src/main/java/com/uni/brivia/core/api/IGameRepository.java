package com.uni.brivia.core.api;

import androidx.lifecycle.LiveData;

import com.uni.brivia.core.data.GameResult;
import com.uni.brivia.core.data.QuestionEntity;

/**
 * TODO:
 */
public interface IGameRepository {

    /**
     * Uploads the answer to the server and calculates the score.
     *
     * @return {@link GameResult} if the answer is correct or not and points received
     */
    GameResult uploadAnswerChoice(String answerId);

    /**
     * Called when the time elapses and user hasn't given an answer.
     * @return {@link GameResult} when the time is up.
     * */
    GameResult timeUp();

    /**
     *
     */
    LiveData<QuestionEntity> getDailyQuestion();

    /**
     * Time in millis to answer a question.
     */
    int TIME_TO_PLAY = 1000 * 10;
}
