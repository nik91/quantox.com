package com.karovic.nikola.themovieapp.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.karovic.nikola.themovieapp.model.Movie;
import com.karovic.nikola.themovieapp.rest.api.MoviesAPI;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TopRatedMoviesViewModel extends BaseViewModel {

    public MutableLiveData<List<Movie>> movieMutableLiveData = new MutableLiveData<>();

    @Inject
    MoviesAPI moviesAPI;

    Disposable topRatedMoviesDisposable;

    @Override
    protected void onCleared() {
        super.onCleared();
        if (topRatedMoviesDisposable != null) {
            topRatedMoviesDisposable.dispose();
        }
    }


    public void getTopRatedMovies(String language, int page) {
        state.postValue(ViewModelState.LOADING);
        topRatedMoviesDisposable = moviesAPI.topRatedMovies(language, page )
                .concatMap(Observable::just)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::OnGetOrderSuccess, this::onFailed);

    }

    private void OnGetOrderSuccess(List<Movie> movies) {
        movieMutableLiveData.setValue(movies);
        state.postValue(ViewModelState.SUCCESS);
    }


}
