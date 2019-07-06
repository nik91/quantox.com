package com.karovic.nikola.themovieapp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.karovic.nikola.themovieapp.model.Genre;
import com.karovic.nikola.themovieapp.model.Language;

import java.util.List;

@Dao
public interface GenreDao {
    @Insert
    void insert(Genre genre);

    @Insert
    void insertAll(List<Genre> genres);

    @Update
    void update(Genre genre);

    @Delete
    void deleteAll(List<Genre> genres);

    @Query("SELECT * FROM Genre order by id ASC")
    List<Genre> getAllGenres();

    @Query("SELECT * FROM Genre WHERE id = :genreId")
    List<Genre> getGenre(int genreId);
}
