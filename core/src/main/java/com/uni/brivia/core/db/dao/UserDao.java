package com.uni.brivia.core.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.uni.brivia.core.data.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    /**
     * TODO: docs
     */
    @Query("SELECT * FROM Users")
    LiveData<List<UserEntity>> getUsers();


    @Query("SELECT * FROM USERS WHERE uid == :userId")
    LiveData<UserEntity> getCurrentUser(String userId);

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity user);

    /**
     * Delete all users.
     */
    @Query("DELETE FROM Users")
    void deleteAllUsers();
}
