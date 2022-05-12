package com.uni.brivia.core.db;


import android.content.Context;

import androidx.room.Room;

import com.uni.brivia.core.db.dao.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class DbModule {
    private static final String DB_NAME = "brivia-db";

    @Provides
    @Singleton
    static AppDatabase provideAppDatabase(@ApplicationContext Context appContext) {
        return Room.databaseBuilder(
                appContext,
                AppDatabase.class,
                DB_NAME).build();
    }

    @Provides
    static UserDao provideUserDao(AppDatabase database) {
        return database.userDao();
    }
}