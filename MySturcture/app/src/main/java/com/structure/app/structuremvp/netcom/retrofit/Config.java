package com.structure.app.structuremvp.netcom.retrofit;


import com.structure.app.structuremvp.BuildConfig;

public interface Config {

    /*
    *
    * BASE URL FOR SERVER COMMUNICATION
    *
    * */
    public static final String BASE_URL = BuildConfig.BASE_URL;

    /**
     * API NAMES
     */
    String LOGIN_URL = "login";
    String FORGOT_PASSWORD_URL = "forgotpassword";
    String CHANGE_PASSWORD_URL = "changepassword";




}

