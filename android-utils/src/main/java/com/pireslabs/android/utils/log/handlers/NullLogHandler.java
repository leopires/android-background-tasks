package com.pireslabs.android.utils.log.handlers;

import com.pireslabs.android.utils.log.LogHandler;

public final class NullLogHandler implements LogHandler {

    @Override
    public void info(String tag, String message) {
        // Do nothing.
    }

    @Override
    public void debug(String tag, String message) {
        // Do nothing.
    }

    @Override
    public void error(String tag, String message) {
        // Do nothing.
    }
}
