package com.karovic.nikola.themovieapp.rest;

import com.karovic.nikola.themovieapp.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    public static final String HEADER_API = "api_key";

    private static final String HEADER_ACCEPT = "Accept";
    private static final String HEADER_JSON = "application/json";

    public HeaderInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header(HEADER_ACCEPT, HEADER_JSON)
                .method(original.method(), original.body());

        requestBuilder.header(HEADER_API, BuildConfig.API_KEY);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}

