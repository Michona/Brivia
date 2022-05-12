package com.uni.brivia.domain;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.uni.brivia.base.AppExecutors;
import com.uni.brivia.db.dao.UserDao;
import com.uni.brivia.db.entity.UserEntity;
import com.uni.brivia.firestore.FirestoreService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * TODO: docs
 */
@Singleton
public class AuthRepository {

    private final UserDao mUserDao;

    private final AppExecutors mExecutors;

    private final FirebaseAuth mFireAuth;

    private final FirestoreService mFirestoreService;

    @Inject
    AuthRepository(@NonNull UserDao userDao, @NonNull FirestoreService firestoreService, @NonNull AppExecutors executors) {
        this.mUserDao = userDao;
        this.mFireAuth = FirebaseAuth.getInstance();
        this.mExecutors = executors;
        this.mFirestoreService = firestoreService;

        subscribeToUsers();
    }

    /**
     * Called after user has been successfully authenticated in. Creates user in Server
     *
     * @see FirestoreService
     */
    public void createNewUser(@NonNull AuthResult authResult) {
        mFirestoreService.createUser(authResult)
                .addOnSuccessListener(res -> {
                    /* After created fetch user collection again. */
                    subscribeToUsers();
                    Timber.d("Document added");
                })
                .addOnFailureListener(Timber::e);
    }

    /**
     * Listens to changes from Server and updates disk.
     * Called when this repository is created.
     */
    private void subscribeToUsers() {
        mFirestoreService.getUserCollection().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    insertToDb(document);
                }
            } else {
                Timber.e("Cannot fetch users.");
            }
        });
    }

    private void insertToDb(QueryDocumentSnapshot document) {
        mExecutors.diskIO().execute(() -> mUserDao.insertUser(FirestoreService.parseUser(document.getData())));
    }

    public LiveData<List<UserEntity>> getUsers() {
        return mUserDao.getUsers();
    }

    /**
     * Goes through all the users in out database and finds the user that matched the ID in {@link FirebaseAuth#getCurrentUser()}.
     *
     * @return LiveData of the current users data (wrapped in {@link UserEntity}), or null if not found.
     */
    @SuppressWarnings("ConstantConditions")
    public LiveData<UserEntity> getCurrentUser() {
        return mUserDao.getCurrentUser(mFireAuth.getCurrentUser().getUid());
    }
}
