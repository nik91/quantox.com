package com.karovic.nikola.themovieapp.dagger.module;

import android.app.Application;

import androidx.room.Room;

import com.karovic.nikola.themovieapp.db.AppDatabase;
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
    AppDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "themovieapp.db")
                .fallbackToDestructiveMigration()
                .build();
    }
//
    @Provides
    @Reusable
    GenreDao providesGenreDao(AppDatabase db) {
        return db.genreDao();
    }

    @Provides
    @Reusable
    LanguagesDao providesLanguagesDao(AppDatabase db) {
        return db.languagesDao();
    }

    @Provides
    @Reusable
    MovieDao providesMovieDao(AppDatabase db) {
        return db.movieDao();
    }

    @Provides
    @Reusable
    MovieDetailsDao providesMovieDetailsDao(AppDatabase db) {
        return db.movieDetailsDao();
    }

}
