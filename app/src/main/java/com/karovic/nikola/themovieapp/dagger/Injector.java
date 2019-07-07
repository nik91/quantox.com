package com.karovic.nikola.themovieapp.dagger;

import com.karovic.nikola.themovieapp.MainApplication;
import com.karovic.nikola.themovieapp.dagger.component.AppComponent;
import com.karovic.nikola.themovieapp.dagger.component.DaggerAppComponent;
import com.karovic.nikola.themovieapp.dagger.component.DaggerViewModelComponent;
import com.karovic.nikola.themovieapp.dagger.component.ViewModelComponent;
import com.karovic.nikola.themovieapp.dagger.module.AppModule;
import com.karovic.nikola.themovieapp.dagger.module.DataModule;
import com.karovic.nikola.themovieapp.dagger.module.NetworkModule;

public enum Injector {
    INSTANCE;
    AppComponent applicationComponent;
    ViewModelComponent viewModelComponent;

    public static void initialize(MainApplication mainApplication) {
        DataModule dataModule = new DataModule();
        INSTANCE.applicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mainApplication))
                .dataModule(dataModule).build();
        INSTANCE.viewModelComponent = DaggerViewModelComponent.builder().networkModule(new NetworkModule())
                .appModule(new AppModule((MainApplication) get().application()))
                .dataModule(dataModule).build();
    }

    public static AppComponent get() {
        return INSTANCE.applicationComponent;
    }
    public static ViewModelComponent getVMComponent() {
        return INSTANCE.viewModelComponent;
    }
}