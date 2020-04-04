package com.example.carddemo;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

public class StartApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("")
                .clientKey("")
                .server("")
                .build()
        );

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
