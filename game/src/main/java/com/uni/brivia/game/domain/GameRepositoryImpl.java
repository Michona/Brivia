package com.uni.brivia.game.domain;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.uni.brivia.core.AppExecutors;
import com.uni.brivia.core.api.IGameRepository;
import com.uni.brivia.core.data.QuestionEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GameRepositoryImpl implements IGameRepository {

    private final AppExecutors mExecutors;

    @Inject
    GameRepositoryImpl(@NonNull AppExecutors executors) {
        this.mExecutors = executors;
    }

    @Override
    public void uploadAnswerChoice(String answerId) {
    }

    @Override
    public LiveData<QuestionEntity> getDailyQuestion() {
        return null;
    }
}
