package com.karovic.nikola.themovieapp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.karovic.nikola.themovieapp.model.Genre;
import com.karovic.nikola.themovieapp.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    void insert(Movie movie);

    @Insert
    void insertAll(List<Movie> movies);

    @Update
    void update(Movie movie);

    @Delete
    void deleteAll(List<Movie> movies);

    @Query("SELECT * FROM movie order by title ASC")
    List<Movie> getAllMovies();

    @Query("DELETE FROM movie WHERE id = :movieID")
    void deleteMovie(int movieID);
}
