package com.karovic.nikola.themovieapp.dagger.module;

import android.app.Application;

import androidx.room.Room;

import com.karovic.nikola.themovieapp.db.TheMovieAppDatabase;
import com.karovic.nikola.themovieapp.db.dao.GenreDao;
import com.karovic.nikola.themovieapp.db.dao.LanguagesDao;
import com.karovic.nikola.themovieapp.db.dao.MovieDao;
import com.karovic.nikola.themovieapp.db.dao.MovieDetailsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public class RoomModule {

    @Provides
    @Singleton
    TheMovieAppDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, TheMovieAppDatabase.class, "themovieapp.db")
                .fallbackToDestructiveMigration()
                .build();
    }
//
    @Provides
    @Reusable
    GenreDao providesGenreDao(TheMovieAppDatabase db) {
        return db.genreDao();
    }

    @Provides
    @Reusable
    LanguagesDao providesLanguagesDao(TheMovieAppDatabase db) {
        return db.languagesDao();
    }

    @Provides
    @Reusable
    MovieDao providesMovieDao(TheMovieAppDatabase db) {
        return db.movieDao();
    }

    @Provides
    @Reusable
    MovieDetailsDao providesMovieDetailsDao(TheMovieAppDatabase db) {
        return db.movieDetailsDao();
    }

}
