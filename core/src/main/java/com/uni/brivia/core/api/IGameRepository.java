package com.uni.brivia.core.api;

import androidx.lifecycle.LiveData;

import com.uni.brivia.core.data.GameResult;
import com.uni.brivia.core.data.QuestionEntity;

/**
 * Contains logic to deal with the game state and answer choices.
 */
public interface IGameRepository {

    /**
     * Uploads the answer to the server and calculates the score.
     *
     * @return {@link GameResult} if the answer is correct or not and points received
     */
    GameResult uploadAnswerChoice(Integer answerId);

    /**
     * Called when the time elapses and user hasn't given an answer.
     *
     * @return {@link GameResult} when the time is up.
     */
    GameResult timeUp();

    /**
     * @return the daily question that the user needs to answer.
     */
    LiveData<QuestionEntity> getDailyQuestion();

    /**
     * Time in millis to answer a question.
     */
    int TIME_TO_PLAY = 1000 * 10;
}
