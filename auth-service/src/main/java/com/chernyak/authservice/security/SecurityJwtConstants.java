package com.chernyak.authservice.security;

public class SecurityJwtConstants {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 60*60;
    public static final long REFRESH_TOKEN_VALIDITY_SECONDS = 60*60;
    public static final String SIGNING_KEY = "this is a signing key :)";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
}