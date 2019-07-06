package com.karovic.nikola.themovieapp.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    private static final String PREFS_DEFAULT = "TheMovieApp";

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return application.getSharedPreferences(PREFS_DEFAULT, Context.MODE_PRIVATE);
    }
}
