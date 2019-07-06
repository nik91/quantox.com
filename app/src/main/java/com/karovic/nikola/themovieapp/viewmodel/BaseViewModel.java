package com.karovic.nikola.themovieapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.karovic.nikola.themovieapp.dagger.Injector;
import com.karovic.nikola.themovieapp.dagger.component.ViewModelComponent;

public class BaseViewModel extends ViewModel {

    private ViewModelComponent injector = Injector.getVMComponent();



    public MutableLiveData<ViewModelState> state = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();

    BaseViewModel() {
        inject();
    }

    private void inject() {
        if (this instanceof OrderHistoryViewModel) {
            injector.inject((OrderHistoryViewModel) this);
        }
        if (this instanceof OrderHistoryDetailsViewModel) {
            injector.inject((OrderHistoryDetailsViewModel) this);
        }


    }


    protected void onFailed(Throwable throwable) {
        throwable.printStackTrace();
        state.postValue(ViewModelState.FAILURE);
        error.postValue(throwable);
    }

}