package com.example.carddemo;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

public class StartApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

//        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("myappID")
                .clientKey("gFZvFAWgcai4")
                .server("http://13.234.59.178/parse/")
                .build()
        );

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
