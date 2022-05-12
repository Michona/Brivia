package com.uni.brivia.core.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


/**
 * TODO: docs
 */
@Entity(tableName = "users")
public class UserEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "uid")
    private String mId;

    @ColumnInfo(name = "name")
    private String mUserName;

    @Nullable
    @ColumnInfo(name = "lastPlayed")
    private Date mLastPlayed;

    @NonNull
    @ColumnInfo(name = "score")
    private Integer mScore;

    public UserEntity(@NonNull String id, String userName, @Nullable Date lastPlayed, @NonNull Integer score) {
        this.mId = id;
        this.mUserName = userName;
        this.mScore = score;
        this.mLastPlayed = lastPlayed;
    }

    public String getId() {
        return mId;
    }

    public String getUserName() {
        return mUserName;
    }

    public Date getLastPlayed() {
        return mLastPlayed;
    }

    public Integer getScore() {
        return mScore;
    }
}
