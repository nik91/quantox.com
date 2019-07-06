package com.karovic.nikola.themovieapp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.karovic.nikola.themovieapp.model.Language;

import java.util.List;

@Dao
public interface LanguagesDao {
    @Insert
    void insert(Language language);

    @Insert
    void insertAll(List<Language> languages);

    @Update
    void update(Language language);

    @Delete
    void deleteAll(List<Language> languages);

    @Query("SELECT * FROM Language order by iso6391 ASC")
    List<Language> getAllLanguages();

    @Query("SELECT * FROM Language WHERE iso6391 = :iso6391")
    List<Language> getLanguage(String iso6391);
}
