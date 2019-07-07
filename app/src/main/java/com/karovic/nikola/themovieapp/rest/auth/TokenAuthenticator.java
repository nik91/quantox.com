package com.karovic.nikola.themovieapp.rest.auth;


import android.util.Log;

import com.karovic.nikola.themovieapp.BuildConfig;
import com.karovic.nikola.themovieapp.rest.HeaderInterceptor;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class TokenAuthenticator implements Authenticator {

    private static final String TAG = "TokenAuthenticator";
    @Inject
    AuthManager authManager;


    @Override
    public synchronized Request authenticate(Route route, Response response) throws IOException {

        Log.d(TAG, "Authentication failed, try to refresh token");

        Request.Builder requestBuilder = response.request().newBuilder();
        requestBuilder.header(HeaderInterceptor.HEADER_API, BuildConfig.API_KEY);

        Log.d(TAG, "Retry request");
        return requestBuilder.build();
    }
}
