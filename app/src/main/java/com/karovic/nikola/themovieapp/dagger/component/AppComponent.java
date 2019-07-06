package com.karovic.nikola.themovieapp.dagger.component;

import android.app.Application;

import com.karovic.nikola.themovieapp.dagger.module.AppModule;
import com.karovic.nikola.themovieapp.dagger.module.DataModule;
import com.karovic.nikola.themovieapp.dagger.module.NetworkModule;
import com.karovic.nikola.themovieapp.dagger.module.RoomModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                DataModule.class,
                RoomModule.class,
                NetworkModule.class
        }
)
public interface AppComponent {
    Application application();


//    void inject(Language language);

//    void inject(UserViewModel userViewModel);
//
//    void inject(CartViewModel cartViewModel);
}
