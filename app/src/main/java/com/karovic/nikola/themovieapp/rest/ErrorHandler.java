package com.karovic.nikola.themovieapp.rest;

import androidx.annotation.Nullable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ErrorHandler {

    @Nullable
    public static String errorMessageFrom(Throwable throwable) {

        ObjectMapper objectMapper = new ObjectMapper();
        if (throwable instanceof HttpException) {
            ResponseBody errorBody = ((HttpException) throwable).response().errorBody();
            if (errorBody != null) {
                String errorString;
                try {
                    errorString = errorBody.string();

                } catch (IOException e) {
                    return null;
                }

                try {
                    TypeReference<HashMap<String,Object>> jsonTypeRef = new TypeReference<HashMap<String,Object>>() {};
                    HashMap<String, Object> errorMap = objectMapper.readValue(errorString, jsonTypeRef);
                    if (errorMap.get("messages") instanceof List) {
                        List messages = (List) errorMap.get("messages");
                        if (messages != null && messages.size() > 0) {
                            if (messages.get(0) instanceof HashMap) {
                                HashMap hashMap = (HashMap) messages.get(0);
                                if (hashMap.get("message") instanceof String) {
                                    return (String) hashMap.get("message");
                                }
                            }
                        }
                    } else if (errorMap.get("message") instanceof String) {
                        return (String) errorMap.get("message");
                    }
                } catch (IOException e) {
                    return errorString;
                }
            }
        }
        return null;
    }


    public static int errorCodeFrom(Throwable throwable) {
        if (throwable instanceof HttpException) {
            return ((HttpException) throwable).code();
        }
        return 0;
    }


}
