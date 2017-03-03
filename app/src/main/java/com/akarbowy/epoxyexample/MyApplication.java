package com.akarbowy.epoxyexample;


import android.app.Application;

import com.facebook.stetho.Stetho;

import timber.log.Timber;

public class MyApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();

        if( BuildConfig.DEBUG) {
            Timber.plant(new TagopDebugTree());
            Stetho.initializeWithDefaults(this);
        }
    }

    private class TagopDebugTree extends Timber.DebugTree {
        @Override
        protected String createStackElementTag(StackTraceElement element) {
            return String.format("%s-%s", super.createStackElementTag(element),
                    element.getMethodName());
        }
    }
}
