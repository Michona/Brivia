package com.uni.brivia.core;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.uni.brivia.core.data.QuestionEntity;
import com.uni.brivia.core.data.UserEntity;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * TODO: docs
 */
@Singleton
public class FirestoreService {

    private final FirebaseFirestore db;

    @Inject
    public FirestoreService() {
        db = FirebaseFirestore.getInstance();
    }

    /**
     * Creates User document to server's database
     *
     * @return the {@link Task} of the document being created.
     */
    public Task<Void> createUser(@NonNull AuthResult authResult) {
        FirebaseUser authUser = authResult.getUser();

        Map<String, Object> user = new HashMap<>();
        user.put(USER_KEY_ID, authUser.getUid());
        user.put(USER_KEY_NAME, authUser.getDisplayName());
        user.put(USER_KEY_TIMESTAMP, null);
        user.put(USER_KEY_SCORE, 0);

        return db.collection(COLLECTION_USERS).document(authUser.getUid()).set(user, SetOptions.merge());
    }

    /**
     * @return the {@link Task} for the query to fetch the collection containing all the users.
     */
    public Task<QuerySnapshot> getUserCollection() {
        return db.collection(COLLECTION_USERS).get();
    }

    /**
     * @return the {@link Task} for the document to fetch the current user from server.
     */
    public Task<DocumentSnapshot> getUserDocument(String uid) {
        return db.collection(COLLECTION_USERS).document(uid).get();
    }

    /**
     * @return the {@link Task} for the query to fetch the collection containing all of the question.
     */
    public Task<QuerySnapshot> getQuestionsCollection() {
        return db.collection(COLLECTION_QUESTIONS).get();
    }

    //region Parsing

    /**
     * Parses the user hashmap we receive from the server into our own {@link UserEntity}
     */
    @SuppressWarnings("ConstantConditions")
    @NonNull
    @Contract("_ -> new")
    public static UserEntity parseUser(@NonNull Map<String, Object> userMap) {
        Date lastPlayedTimestamp;
        if (userMap.get(USER_KEY_TIMESTAMP) == null) {
            lastPlayedTimestamp = null;
        } else {
            lastPlayedTimestamp = new Date((Long) userMap.get(USER_KEY_TIMESTAMP));
        }

        return new UserEntity(
                (String) userMap.get(USER_KEY_ID),
                (String) userMap.get(USER_KEY_NAME),
                lastPlayedTimestamp,
                Math.toIntExact((Long) userMap.get(USER_KEY_SCORE))
        );
    }


    /**
     * Parses the question hashmap we receive from the server into our own {@link QuestionEntity}
     */
    @SuppressWarnings({"ConstantConditions", "unchecked"})
    @NonNull
    public static QuestionEntity parseQuestion(@NonNull Map<String, Object> questionMap) {
        return new QuestionEntity(
                (String) questionMap.get(ID_KEY),
                (String) questionMap.get(CONTENT_KEY),
                (ArrayList<String>) questionMap.get(ANSWERS_KEY),
                Math.toIntExact((Long) questionMap.get(CORRECT_ANSWER_KEY)));
    }

    //endregion

    private static final String COLLECTION_USERS = "users";
    private static final String USER_KEY_NAME = "name";
    private static final String USER_KEY_ID = "uid";
    private static final String USER_KEY_TIMESTAMP = "last-played-timestamp";
    private static final String USER_KEY_SCORE = "score";


    private static final String COLLECTION_QUESTIONS = "questions";
    private static final String CONTENT_KEY = "content";
    private static final String ID_KEY = "id";
    private static final String CORRECT_ANSWER_KEY = "correct-answer-id";
    private static final String ANSWERS_KEY = "answer-options";
}
