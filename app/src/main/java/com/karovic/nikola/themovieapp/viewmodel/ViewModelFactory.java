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

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(OrderHistoryViewModel.class)) {
            return (T) new OrderHistoryViewModel();
        }

        if (modelClass.isAssignableFrom(OrderHistoryDetailsViewModel.class)) {
            return (T) new OrderHistoryDetailsViewModel();
        }



        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}