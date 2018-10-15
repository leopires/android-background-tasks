package com.pireslabs.android.utils.log;

import com.pireslabs.android.utils.BuildConfig;
import com.pireslabs.android.utils.log.handlers.LogCatHandler;
import com.pireslabs.android.utils.log.handlers.NullLogHandler;


public final class Log {

    private static Log SINGLETON;

    private final LogHandler logHandler;

    private Log() {
        if (BuildConfig.DEBUG) {
            this.logHandler = new LogCatHandler();
        } else {
            this.logHandler = new NullLogHandler();
        }
    }

    private static void initLog() {
        if (SINGLETON == null) {
            SINGLETON = new Log();
        }
    }

    public static synchronized void info(String tag, String message) {
        initLog();
        SINGLETON.logHandler.info(tag, message);
    }

    public static synchronized void debug(String tag, String message) {
        initLog();
        SINGLETON.logHandler.debug(tag, message);
    }

    public static synchronized void error(String tag, String message) {
        initLog();
        SINGLETON.logHandler.error(tag, message);
    }
}
