package com.uni.brivia.base;

import android.app.Application;

import com.uni.brivia.BuildConfig;

import static timber.log.Timber.DebugTree;

import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;

@HiltAndroidApp
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        }
    }
}
