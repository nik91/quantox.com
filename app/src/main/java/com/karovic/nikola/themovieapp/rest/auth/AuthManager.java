package com.karovic.nikola.themovieapp.rest.auth;


public class AuthManager {

    private final static String OAUTH2_TOKEN_KEY = "oauth2Token";




    public enum AuthType {
        AUTH_NONE("None");

        public final String type;

        AuthType(String type) {
            this.type = type;
        }
    }

    public AuthType authType = AuthType.AUTH_NONE;

}

