package com.uni.brivia.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uni.brivia.core.data.UserEntity;
import com.uni.brivia.domain.AuthRepository;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final AuthRepository mAuthRepository;

    public Date todayDate;

    @Inject
    HomeViewModel(@NonNull AuthRepository authRepository) {
        this.mAuthRepository = authRepository;
        this.todayDate = new Date(System.currentTimeMillis());
    }

    /**
     * @return the current user from the {@link AuthRepository}
     */
    public LiveData<UserEntity> getCurrentUser() {
        return mAuthRepository.getCurrentUser();
    }

    /**
     * @return true if the user can play the game today.
     */
    public Boolean canUserPlay(UserEntity user) {
        if (user == null) {
            return false;
        }
        if (user.getLastPlayed() == null) {
            return true;
        }
        long diff = todayDate.getTime() - user.getLastPlayed().getTime();
        return TimeUnit.MILLISECONDS.toHours(diff) >= 24;
    }
}
