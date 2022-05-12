package com.uni.brivia.game.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uni.brivia.core.api.IGameRepository;
import com.uni.brivia.core.data.GameResult;
import com.uni.brivia.core.data.QuestionEntity;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GameViewModel extends ViewModel {

    private final IGameRepository mGameRepository;

    /**
     * If null then result not received yet.
     *
     * @see GameResult
     */
    public MutableLiveData<GameResult> result;

    @Inject
    public GameViewModel(@NonNull IGameRepository gameRepository) {
        this.mGameRepository = gameRepository;
        result = new MutableLiveData<>(null);
    }

    public void onAnswerClicked(int positionId) {
        result.postValue(mGameRepository.uploadAnswerChoice(positionId));
    }

    public void timeUp() {
        result.postValue(mGameRepository.timeUp());
    }

    public void clear() {
        result.postValue(null);
    }

    public LiveData<QuestionEntity> getQuestion() {
        return mGameRepository.getDailyQuestion();
    }
}