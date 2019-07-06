package com.karovic.nikola.themovieapp;

import android.app.Application;

import com.karovic.nikola.themovieapp.dagger.Injector;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.initialize(this);
    }
}
