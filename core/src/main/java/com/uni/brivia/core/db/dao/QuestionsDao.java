package com.uni.brivia.core.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.uni.brivia.core.data.QuestionEntity;

import java.util.List;

@Dao
public interface QuestionsDao {

    @Query("SELECT * FROM questions")
    LiveData<List<QuestionEntity>> getQuestions();

    /**
     * Insert a question in the database. If the question already exists, replace it.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(QuestionEntity questionEntity);

}
