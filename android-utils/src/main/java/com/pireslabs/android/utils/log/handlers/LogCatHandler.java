package com.pireslabs.android.utils.log.handlers;

import android.util.Log;

import com.pireslabs.android.utils.log.LogHandler;

public final class LogCatHandler implements LogHandler {

    @Override
    public void info(String tag, String message) {
        this.log(Log.INFO, tag, message);
    }

    @Override
    public void debug(String tag, String message) {
        this.log(Log.DEBUG, tag, message);
    }

    @Override
    public void error(String tag, String message) {
        this.log(Log.ERROR, tag, message);
    }

    private void log(int priority, String tag, String message) {
        Log.println(priority, tag, message);
    }
}
