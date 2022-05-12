package com.uni.brivia.core.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.uni.brivia.core.data.UserEntity;
import com.uni.brivia.core.db.dao.UserDao;

/**
 * The Room database that contains the Users table
 */
@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
