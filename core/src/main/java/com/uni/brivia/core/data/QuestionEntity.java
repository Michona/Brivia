package com.uni.brivia.core.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "questions")
public class QuestionEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final Integer mId;

    @NonNull
    @ColumnInfo(name = "content")
    private final String mContent;

    @NonNull
    @ColumnInfo(name = "answers")
    private final List<AnswerEntity> mAnswers;

    @NonNull
    @ColumnInfo(name = "correct-answer-id")
    private final String mCorrectAnswerId;

    public QuestionEntity(@NonNull Integer id, @NonNull String content, @NonNull List<AnswerEntity> answers, @NonNull String answerId) {
        this.mId = id;
        this.mContent = content;
        this.mAnswers = answers;
        this.mCorrectAnswerId = answerId;
    }

    @NonNull
    public Integer getId() {
        return mId;
    }

    @NonNull
    public String getContent() {
        return mContent;
    }

    @NonNull
    public List<AnswerEntity> getAnswers() {
        return mAnswers;
    }

    @NonNull
    public String getCorrectAnswerId() {
        return mCorrectAnswerId;
    }
}
