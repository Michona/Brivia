package com.uni.brivia.game.domain;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.uni.brivia.core.AppExecutors;
import com.uni.brivia.core.FirestoreService;
import com.uni.brivia.core.api.IGameRepository;
import com.uni.brivia.core.data.GameResult;
import com.uni.brivia.core.data.QuestionEntity;
import com.uni.brivia.core.db.dao.QuestionsDao;

import java.util.Calendar;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

@Singleton
public class GameRepositoryImpl implements IGameRepository {

    private final AppExecutors mExecutors;

    private final FirestoreService mFirestoreService;

    private final QuestionsDao mQuestionsDao;

    private final FirebaseAuth mFireAuth;

    /**
     * Chosen from the list of questions based on the today date
     * Answers from users are compared to this.
     *
     * @see #getRandomQuestionPosition()
     */
    private QuestionEntity mTodayQuestion;

    @Inject
    GameRepositoryImpl(@NonNull AppExecutors executors, @NonNull FirestoreService firestoreService, @NonNull QuestionsDao questionsDao) {
        this.mExecutors = executors;
        this.mFirestoreService = firestoreService;
        this.mQuestionsDao = questionsDao;
        this.mFireAuth = FirebaseAuth.getInstance();

        fetchQuestions();
    }

    @Override
    public GameResult uploadAnswerChoice(Integer answerId) {

        if (mTodayQuestion.getCorrectAnswerId().equals(answerId)) {
            /* Question is correct! */
            updateUserScore(POINTS_FOR_SUCCESS + POINTS_FOR_TRYING);
            return new GameResult(true, POINTS_FOR_SUCCESS, POINTS_FOR_TRYING);
        } else {
            /* Question is not correct */
            updateUserScore(POINTS_FOR_ERROR + POINTS_FOR_TRYING);
            return new GameResult(false, POINTS_FOR_ERROR, POINTS_FOR_TRYING);
        }
    }

    @Override
    public GameResult timeUp() {
        updateUserScore(POINTS_FOR_ERROR + POINTS_FOR_TRYING);
        return new GameResult(false, POINTS_FOR_ERROR, POINTS_FOR_TRYING);
    }

    @Override
    public LiveData<QuestionEntity> getDailyQuestion() {
        return Transformations.map(mQuestionsDao.getQuestions(), questions -> {
            if (questions.isEmpty()) {
                return null;
            } else {
                try {
                    mTodayQuestion = questions.stream().sorted(new QuestionsComparator()).collect(Collectors.toList()).get(getRandomQuestionPosition());
                } catch (Exception ignored) {
                }
                return mTodayQuestion;
            }
        });
    }

    private void updateUserScore(Integer points) {
        mFirestoreService.updateUserScore(mFireAuth.getUid(), points);
    }

    /**
     * Based on today's date it chooses an id for question.
     * It's so that everyone gets the same question for the same day.
     */
    @NonNull
    private Integer getRandomQuestionPosition() {
        int dayInMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return dayInMonth % QUESTION_MAX;
    }

    private void fetchQuestions() {
        mFirestoreService.getQuestionsCollection().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    insertToDb(document);
                }
            } else {
                Timber.e("Cannot fetch questions.");
            }
        });
    }

    private void insertToDb(QueryDocumentSnapshot document) {
        mExecutors.diskIO().execute(() -> mQuestionsDao.insert(FirestoreService.parseQuestion(document.getData())));
    }

    private static class QuestionsComparator implements Comparator<QuestionEntity> {
        @Override
        public int compare(QuestionEntity current, QuestionEntity next) {
            return current.getId().compareTo(next.getId());
        }
    }

    /**
     * TODO: This will be dynamic in the future
     */
    private static final int POINTS_FOR_SUCCESS = 12;
    private static final int POINTS_FOR_ERROR = -5;
    private static final int POINTS_FOR_TRYING = 2;

    private static final int QUESTION_MAX = 2;
}
