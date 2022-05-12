package com.uni.brivia.viewmodels;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uni.brivia.db.entity.UserEntity;
import com.uni.brivia.domain.AuthRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final AuthRepository mAuthRepository;

    @Inject
    HomeViewModel(@NonNull AuthRepository authRepository) {
        this.mAuthRepository = authRepository;
    }

    /**
     * @return the current user from the {@link AuthRepository}
     */
    public LiveData<UserEntity> getCurrentUser() {
        return mAuthRepository.getCurrentUser();
    }
}
