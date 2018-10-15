package com.pireslabs.backgroundtasks;

import android.app.Application;

import com.pireslabs.android.utils.log.Log;

public final class BackgroundTasks extends Application {

    private static final String TAG = BackgroundTasks.class.getSimpleName();

    private static BackgroundTasks INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Log.info(TAG, String.format("Debug Mode: %s", BuildConfig.DEBUG));
    }

    public static BackgroundTasks getInstance() {
        return INSTANCE;
    }
}
