package com.karovic.nikola.themovieapp.rest.api;

import com.karovic.nikola.themovieapp.model.Movie;
import com.karovic.nikola.themovieapp.rest.response.MostPopularMoviesResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesAPI {

    @GET("top_rated")
    Observable<MostPopularMoviesResult> topRatedMovies(@Query("language") String language,
                                           @Query("page") int page,
                                           @Query("api_key") String apiKey);

    @GET("{movieId}/recommendations")
    Observable<MostPopularMoviesResult> relatedMovies(@Path("") int movieId,
                                                      @Query("language") String language,
                                                      @Query("page") int page,
                                                      @Query("api_key") String apiKey);
}
