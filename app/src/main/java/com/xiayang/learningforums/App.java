package com.xiayang.learningforums;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Date: 2020/2/17
 * Author: aaronzzxup@gmail.com
 */
public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
