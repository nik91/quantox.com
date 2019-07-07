package com.karovic.nikola.themovieapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.karovic.nikola.themovieapp.dagger.Injector;
import com.karovic.nikola.themovieapp.dagger.component.AppComponent;
import com.karovic.nikola.themovieapp.dagger.component.ViewModelComponent;


public class BaseViewModel extends ViewModel {
    private AppComponent appInjector = Injector.get();
    private ViewModelComponent injector = Injector.getVMComponent();


    public MutableLiveData<ViewModelState> state = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();

    BaseViewModel() {
        inject();
    }

    private void inject() {
        if (this instanceof TopRatedMoviesViewModel) {
            injector.inject((TopRatedMoviesViewModel) this);
        }

    }


    protected void onFailed(Throwable throwable) {
        throwable.printStackTrace();
        state.postValue(ViewModelState.FAILURE);
        error.postValue(throwable);
    }

}