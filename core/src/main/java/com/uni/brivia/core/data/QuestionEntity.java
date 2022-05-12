package com.uni.brivia.core.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class QuestionEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer mId;

    @NonNull
    @ColumnInfo(name = "title")
    private String mTitle;

    @NonNull
    @ColumnInfo(name = "points")
    private Integer mPoints;

    public QuestionEntity(@NonNull Integer id, @NonNull String title, @NonNull Integer points) {
        this.mId = id;
        this.mTitle = title;
        this.mPoints = points;
    }

    @NonNull
    public Integer getId() {
        return mId;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @NonNull
    public Integer getPoints() {
        return mPoints;
    }
}
