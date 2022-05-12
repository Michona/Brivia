package com.uni.brivia.base;


import android.content.Context;

import androidx.room.Room;

import com.uni.brivia.core.api.IAuthRepository;
import com.uni.brivia.core.api.IGameRepository;
import com.uni.brivia.core.db.AppDatabase;
import com.uni.brivia.core.db.dao.QuestionsDao;
import com.uni.brivia.core.db.dao.UserDao;
import com.uni.brivia.domain.AuthRepositoryImpl;
import com.uni.brivia.game.domain.GameRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {
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

    @Provides
    static QuestionsDao provideQuestionsDao(AppDatabase database) {
        return database.questionsDao();
    }

    @Binds
    abstract IAuthRepository bindAuthRepository(AuthRepositoryImpl impl);

    @Binds
    abstract IGameRepository bindGameRepository(GameRepositoryImpl impl);
}