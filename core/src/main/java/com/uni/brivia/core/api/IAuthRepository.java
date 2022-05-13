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

    /**
     * @return The current {@link UserEntity}
     */
    @NonNull
    LiveData<UserEntity> getCurrentUser();

    /**
     * @return All the users that have an account.
     */
    @NonNull
    LiveData<List<UserEntity>> getUsers();

    /**
     * Fetches users from server and saves to our Room Db.
     */
    void syncUsers();

    /**
     * Creates a new user in our server based on the result of the authentication with Firebase and Google.
     */
    void createNewUser(@NonNull AuthResult authResult);
}
