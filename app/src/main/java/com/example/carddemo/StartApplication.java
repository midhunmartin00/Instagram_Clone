package com.example.carddemo;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

public class StartApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("3f8e36ed475e37774f1481eb8688b67c635242d9")
                .clientKey("f40ce6c42ba27624e4b1ea8bfc3f4a48e78e1cd3")
                .server("http://13.232.64.11:80/parse/")
                .build()
        );

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
