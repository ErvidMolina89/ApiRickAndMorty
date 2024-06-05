package com.wposs.apirickandmorty.Base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class ContextApp extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context context) {
        mContext = context;
    }
}
