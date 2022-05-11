package com.uni.brivia.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.uni.brivia.db.dao.UserDao;
import com.uni.brivia.db.entity.UserEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 */
@Singleton
public class AuthRepository {

    private final UserDao mUserDao;

    private final FirebaseAuth mFireAuth;

    @Inject
    AuthRepository(UserDao userDao) {
        this.mUserDao = userDao;
        this.mFireAuth = FirebaseAuth.getInstance();
    }

    /**
     *
     */
    LiveData<List<UserEntity>> getUsers() {
        return mUserDao.getUsers();
    }

    public LiveData<UserEntity> getCurrentUser() {
        return Transformations.map(getUsers(), users -> {
            // TODO: find the user matchng the uid from fireauth.
            return users.get(0);
        });
    }
}
