package com.karovic.nikola.themovieapp.viewmodel;

import android.os.Build;

import androidx.lifecycle.MutableLiveData;

import com.karovic.nikola.themovieapp.BuildConfig;
import com.karovic.nikola.themovieapp.model.Movie;
import com.karovic.nikola.themovieapp.rest.api.MoviesAPI;
import com.karovic.nikola.themovieapp.rest.response.MostPopularMoviesResult;

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
        topRatedMoviesDisposable = moviesAPI.topRatedMovies(language, page, BuildConfig.API_KEY)
                .concatMap(Observable::just)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::OnGetOrderSuccess, this::onFailed);

    }

    private void OnGetOrderSuccess(MostPopularMoviesResult result) {
        movieMutableLiveData.setValue(result.getResults());
        state.postValue(ViewModelState.SUCCESS);
    }


}
