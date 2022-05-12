package com.uni.brivia.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.uni.brivia.core.data.UserEntity;
import com.uni.brivia.domain.AuthRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ScoresViewModel extends ViewModel {

    private final AuthRepository mAuthRepository;

    @Inject
    ScoresViewModel(@NonNull AuthRepository authRepository) {
        this.mAuthRepository = authRepository;
    }

    /**
     * @return all users from the database, sorted by their highscores.
     */
    public LiveData<List<UserEntity>> getAllUsers() {
        return Transformations.map(mAuthRepository.getUsers(), users -> users.stream().sorted(new ScoreComparator()).collect(Collectors.toList()));
    }

    private static class ScoreComparator implements Comparator<UserEntity> {
        @Override
        public int compare(UserEntity current, UserEntity next) {
            if (current.getScore().equals(next.getScore())) {
                return 0;
            } else if (current.getScore() > next.getScore()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
