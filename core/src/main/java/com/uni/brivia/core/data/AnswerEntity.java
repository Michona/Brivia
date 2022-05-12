package com.uni.brivia.core.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class AnswerEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final Integer mId;

    @NonNull
    @ColumnInfo(name = "content")
    private final String mContent;

    public AnswerEntity(@NonNull Integer id, @NonNull String content) {
        this.mId = id;
        this.mContent = content;
    }


    @NonNull
    public Integer getId() {
        return mId;
    }

    @NonNull
    public String getContent() {
        return mContent;
    }
}
