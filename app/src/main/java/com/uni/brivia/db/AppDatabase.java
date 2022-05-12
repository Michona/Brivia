package com.uni.brivia.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.uni.brivia.db.dao.UserDao;
import com.uni.brivia.db.entity.UserEntity;

/**
 * The Room database that contains the Users table
 */
@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
