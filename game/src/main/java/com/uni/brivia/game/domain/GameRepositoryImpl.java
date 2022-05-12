package com.uni.brivia.game.domain;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.uni.brivia.core.AppExecutors;
import com.uni.brivia.core.api.IGameRepository;
import com.uni.brivia.core.data.GameResult;
import com.uni.brivia.core.data.QuestionEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GameRepositoryImpl implements IGameRepository {

    private final AppExecutors mExecutors;

    @Inject
    GameRepositoryImpl(@NonNull AppExecutors executors) {
        this.mExecutors = executors;

        // todo: sync with firestore and update room.
    }

    @Override
    public GameResult uploadAnswerChoice(String answerId) {
        return new GameResult(true, 10, 2);
    }

    @Override
    public GameResult timeUp() {
        return new GameResult(false, -5, 2);
    }

    @Override
    public LiveData<QuestionEntity> getDailyQuestion() {
        return null;
    }
}
