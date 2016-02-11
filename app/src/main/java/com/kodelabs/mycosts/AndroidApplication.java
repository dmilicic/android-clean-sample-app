package com.kodelabs.mycosts;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

/**
 * Created by dmilicic on 12/10/15.
 */
public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // init database
        FlowManager.init(this);

        // enable logging
        Timber.plant(new DebugTree());
    }

}
