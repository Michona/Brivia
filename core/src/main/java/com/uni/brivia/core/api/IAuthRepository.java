package com.uni.brivia.core.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.AuthResult;
import com.uni.brivia.core.data.UserEntity;

import java.util.List;

/**
 * Contains logic to deal with users, syncing and fetching the current user.
 */
public interface IAuthRepository {

    @NonNull
    LiveData<UserEntity> getCurrentUser();

    @NonNull
    LiveData<List<UserEntity>> getUsers();

    /**
     * Fetches users from server and saves to our Room Db.
     */
    void syncUsers();

    void createNewUser(@NonNull AuthResult authResult);
}
