package com.unige.encode.configuration;

public class SecurityConstants {

   //public static final String SECRET = "SecretKeyToGenJWTs";
   // public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String HEADER_ROLE = "Role";
    public static final String SIGN_UP_URL = "/users/registration";
    public static final String USER_URL = "/user/**";
    public static final String ADMIN_URL = "/admin/**";
    public static final String LOGIN_URL = "encode/login";

    public static final String HEADER= "Authorization";
    public static final String SECRET = "mySecret";
    public static final int EXPIRATION = 604800;
    public static final String PATH = "auth";
    public static final String REFRESH = "refresh";



}
