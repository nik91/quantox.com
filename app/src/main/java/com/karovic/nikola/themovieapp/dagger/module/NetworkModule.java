package com.karovic.nikola.themovieapp.dagger.module;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karovic.nikola.themovieapp.BuildConfig;
import com.karovic.nikola.themovieapp.rest.HeaderInterceptor;
import com.karovic.nikola.themovieapp.rest.api.MoviesAPI;
import com.karovic.nikola.themovieapp.rest.auth.AuthManager;
import com.karovic.nikola.themovieapp.rest.auth.TokenAuthenticator;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetworkModule {


    @Provides
    @Named("apiUrl")
    String provideApiURL() {
        return BuildConfig.API_URL + "/";
    }

    @Provides
    @Named("imagesUrl")
    String provideWebUrl() {
        return BuildConfig.IMAGES_URL + "/";
    }


    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;        // 10 MB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    HeaderInterceptor provideHeaderInterceptor() {
        return new HeaderInterceptor();
    }

    @Provides
    @Singleton
    Authenticator provideAuthenticator() {
        return new TokenAuthenticator();
    }


    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return logging;
    }


    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache,
                                     HeaderInterceptor headerInterceptor,
                                     HttpLoggingInterceptor loggingInterceptor) {
        Interceptor headerInterceptors = new HttpLoggingInterceptor();

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptors)
                .addInterceptor(headerInterceptor);
//                .authenticator(authenticator);
        client.connectTimeout(30, TimeUnit.SECONDS);
        client.readTimeout(30, TimeUnit.SECONDS);
        client.writeTimeout(30, TimeUnit.SECONDS);
        return client.build();
    }

    @Provides
    @Reusable
    Retrofit provideRetrofit(@Named("apiUrl") String apiUrl, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(apiUrl)
                .client(okHttpClient)
                .build();
    }


    @Provides
    @Reusable
    MoviesAPI provideMoviesAPI(Retrofit retrofit) {
        return retrofit.create(MoviesAPI.class);
    }
//
//    @Provides
//    @Reusable
//    UserAPI provideUserApi(Retrofit retrofit) {
//        return retrofit.create(UserAPI.class);
//    }
//
//    @Provides
//    @Reusable
//    ProductsSearchAPI provideSearch(Retrofit retrofit) {
//        return retrofit.create(ProductsSearchAPI.class);
//    }
//
//    @Provides
//    @Reusable
//    ProductsCategoriesAPI provideProductsCategoriesAPI(Retrofit retrofit) {
//        return retrofit.create(ProductsCategoriesAPI.class);
//    }
//
//    @Provides
//    @Reusable
//    ProductDetailsAPI provideProductDetailsAPI(Retrofit retrofit) {
//        return retrofit.create(ProductDetailsAPI.class);
//    }
//
//    @Provides
//    @Reusable
//    CountriesAPI provideCountriesAPI(Retrofit retrofit) {
//        return retrofit.create(CountriesAPI.class);
//    }
//
//    @Provides
//    @Reusable
//    UserAddressesAPI provideUserAddressesAPI(Retrofit retrofit) {
//        return retrofit.create(UserAddressesAPI.class);
//    }
//
//    @Provides
//    @Reusable
//    ShippingServiceAPI provideShippingServiceAPI(Retrofit retrofit) {
//        return retrofit.create(ShippingServiceAPI.class);
//    }
//
//    @Provides
//    @Reusable
//    OrderAPI provideOrderAPI(Retrofit retrofit) {
//        return retrofit.create(OrderAPI.class);
//    }
//
//    @Provides
//    @Reusable
//    PaymentAPI providePaymentApi(Retrofit retrofit) {
//        return retrofit.create(PaymentAPI.class);
//    }
//
//    @Provides
//    @Reusable
//    InvoiceAPI provideOrderHistoryAPI(Retrofit retrofit) {
//        return retrofit.create(InvoiceAPI.class);
//    }
//
//    @Provides
//    @Reusable
//    CurrenciesAPI provideCurrenciesAPI(Retrofit retrofit) {
//        return retrofit.create(CurrenciesAPI.class);
//    }
//
//
//    @Provides
//    @Reusable
//    EmailPreferencesAPI provideEmailPreferencesAPI(Retrofit retrofit) {
//        return retrofit.create(EmailPreferencesAPI.class);
//    }
//
//
//    @Provides
//    @Reusable
//    SavedCreditCardsAPI provideSavedCreditCardsAPI(Retrofit retrofit) {
//        return retrofit.create(SavedCreditCardsAPI.class);
//    }
//
//    @Provides
//    @Reusable
//    CouponsAPI provideCouponsAPI(Retrofit retrofit) {
//        return retrofit.create(CouponsAPI.class);
//    }
}