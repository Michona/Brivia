package com.uni.brivia.core.api;

import androidx.lifecycle.LiveData;

import com.uni.brivia.core.data.QuestionEntity;

/**
 * TODO:
 */
public interface IGameRepository {

    /**
     *
     */
    public void uploadAnswerChoice(String answerId);

    /**
     *
     */
    public LiveData<QuestionEntity> getDailyQuestion();
}
