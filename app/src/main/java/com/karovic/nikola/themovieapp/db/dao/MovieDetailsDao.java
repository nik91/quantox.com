package com.karovic.nikola.themovieapp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.karovic.nikola.themovieapp.model.MovieDetails;

import java.util.List;

@Dao
public interface MovieDetailsDao {
    @Insert
    void insert(MovieDetails movieDetails);

    @Insert
    void insertAll(List<MovieDetails> moviesDetails);

    @Update
    void update(MovieDetails movieDetails);

    @Delete
    void deleteAll(List<MovieDetails> moviesDetails);

    @Query("SELECT * FROM moviedetails WHERE id = :movieId")
    List<MovieDetails> getMovieDetails(int movieId);

    @Query("DELETE FROM moviedetails WHERE id = :movieId")
    void deleteMovieDetails(int movieId);

}
