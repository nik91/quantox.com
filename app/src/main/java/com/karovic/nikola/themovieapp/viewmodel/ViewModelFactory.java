package com.karovic.nikola.themovieapp.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;

public class ViewModelFactory implements Factory {

    private Context context;

    public ViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TopRatedMoviesViewModel.class)) {
            return (T) new TopRatedMoviesViewModel();
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}