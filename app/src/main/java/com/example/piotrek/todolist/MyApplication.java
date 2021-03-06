package com.example.piotrek.todolist;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        initTimber();
        initLeakCanary();
    }

    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
