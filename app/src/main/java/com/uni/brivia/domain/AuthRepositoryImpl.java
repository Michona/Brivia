package com.uni.brivia.domain;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.uni.brivia.core.AppExecutors;
import com.uni.brivia.core.FirestoreService;
import com.uni.brivia.core.api.IAuthRepository;
import com.uni.brivia.core.data.UserEntity;
import com.uni.brivia.core.db.dao.UserDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

@Singleton
public class AuthRepositoryImpl implements IAuthRepository {

    private final UserDao mUserDao;

    private final AppExecutors mExecutors;

    private final FirebaseAuth mFireAuth;

    private final FirestoreService mFirestoreService;

    @Inject
    AuthRepositoryImpl(@NonNull UserDao userDao, @NonNull FirestoreService firestoreService, @NonNull AppExecutors executors) {
        this.mUserDao = userDao;
        this.mFireAuth = FirebaseAuth.getInstance();
        this.mExecutors = executors;
        this.mFirestoreService = firestoreService;

        fetchUsers();
    }

    /**
     * @return all the users that are saved in the DataBase.
     */
    @NonNull
    @Override
    public LiveData<List<UserEntity>> getUsers() {
        return mUserDao.getUsers();
    }

    /**
     * Goes through all the users in our database and finds the user that matched the ID in {@link FirebaseAuth#getCurrentUser()}.
     *
     * @return LiveData of the current users data (wrapped in {@link UserEntity}), or null if not found.
     */
    @NonNull
    @Override
    @SuppressWarnings("ConstantConditions")
    public LiveData<UserEntity> getCurrentUser() {
        return mUserDao.getCurrentUser(mFireAuth.getCurrentUser().getUid());
    }

    /**
     * Called after user has been successfully authenticated in. Creates user in Server
     *
     * @see FirestoreService
     */
    @Override
    public void createNewUser(@NonNull AuthResult authResult) {
        mFirestoreService.createUser(authResult)
                .addOnSuccessListener(res -> {
                    /* After created fetch user collection again. */
                    fetchUsers();
                })
                .addOnFailureListener(Timber::e);
    }

    /**
     * Listens to changes from Server and updates disk.
     * Called when this repository is created.
     */
    private void fetchUsers() {
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
        Timber.d("Inserting to DB");
        mExecutors.diskIO().execute(() -> mUserDao.insertUser(FirestoreService.parseUser(document.getData())));
    }
}
