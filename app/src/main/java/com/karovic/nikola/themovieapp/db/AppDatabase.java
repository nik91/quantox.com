package com.karovic.nikola.themovieapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.karovic.nikola.themovieapp.db.dao.GenreDao;
import com.karovic.nikola.themovieapp.db.dao.LanguagesDao;
import com.karovic.nikola.themovieapp.db.dao.MovieDao;
import com.karovic.nikola.themovieapp.db.dao.MovieDetailsDao;
import com.karovic.nikola.themovieapp.model.Genre;
import com.karovic.nikola.themovieapp.model.Language;
import com.karovic.nikola.themovieapp.model.Movie;
import com.karovic.nikola.themovieapp.model.MovieDetails;

@Database(entities = {Genre.class, Language.class, Movie.class, MovieDetails.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GenreDao genreDao();

    public abstract LanguagesDao languagesDao();

    public abstract MovieDao movieDao();

    public abstract MovieDetailsDao movieDetailsDao();

}
