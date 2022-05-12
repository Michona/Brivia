package com.uni.brivia.core.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "questions")
public class QuestionEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String mId;

    @NonNull
    @ColumnInfo(name = "content")
    private String mContent;

    @NonNull
    @ColumnInfo(name = "answer-options")
    private ArrayList<String> mAnswers;

    @NonNull
    @ColumnInfo(name = "correct-answer-id")
    private Integer mCorrectAnswerId;

    public QuestionEntity(@NonNull String id, @NonNull String content, @NonNull ArrayList<String> answers, @NonNull Integer correctAnswerId) {
        this.mId = id;
        this.mContent = content;
        this.mAnswers = answers;
        this.mCorrectAnswerId = correctAnswerId;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @NonNull
    public String getContent() {
        return mContent;
    }

    @NonNull
    public ArrayList<String> getAnswers() {
        return mAnswers;
    }

    @NonNull
    public Integer getCorrectAnswerId() {
        return mCorrectAnswerId;
    }
}
