package com.atozmak.devtfdemo;

import android.app.Application;

import java.io.File;

import butterknife.ButterKnife;

/**
 * Created by Mak on 2016/3/18.
 */
public class AppApplication extends Application {

File file;
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

