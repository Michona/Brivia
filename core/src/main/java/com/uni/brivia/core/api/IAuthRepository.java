package com.uni.brivia.core.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.AuthResult;
import com.uni.brivia.core.data.UserEntity;

import java.util.List;

/**
 * TODO: docs
 */
public interface IAuthRepository {

    @NonNull
    LiveData<UserEntity> getCurrentUser();

    @NonNull
    LiveData<List<UserEntity>> getUsers();

    void createNewUser(@NonNull AuthResult authResult);
}
