package com.karovic.nikola.themovieapp.rest.api;

import com.karovic.nikola.themovieapp.model.Movie;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesAPI {

    @GET("top_rated")
    Observable<List<Movie>> topRatedMovies(@Query("language") String language,
                                          @Query("page") int page);

    @GET("{movieId}/recommendations")
    Observable<Movie> relatedMovies(@Path("") int movieId,
                                    @Query("language") String language,
                                    @Query("page") int page);
}
