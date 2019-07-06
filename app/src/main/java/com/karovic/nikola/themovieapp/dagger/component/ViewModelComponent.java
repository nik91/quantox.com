package com.karovic.nikola.themovieapp.dagger.component;

import com.karovic.nikola.themovieapp.dagger.module.AppModule;
import com.karovic.nikola.themovieapp.dagger.module.DataModule;
import com.karovic.nikola.themovieapp.dagger.module.NetworkModule;
import com.karovic.nikola.themovieapp.dagger.module.RoomModule;
import com.karovic.nikola.themovieapp.viewmodel.BaseViewModel;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DataModule.class, RoomModule.class})
public interface ViewModelComponent {

    @Component.Builder
    interface Builder {
        ViewModelComponent build();

        Builder appModule(AppModule appModule);

        Builder networkModule(NetworkModule networkModule);

        Builder dataModule(DataModule dataModule);
    }

    void inject(BaseViewModel baseViewModel);

//    void inject(SavedCreditCardsViewModel savedCreditCardsViewModel);

}