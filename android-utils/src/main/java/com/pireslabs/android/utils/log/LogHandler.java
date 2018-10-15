package com.pireslabs.android.utils.log;

public interface LogHandler {

    void info(String tag, String message);

    void debug(String tag, String message);

    void error(String tag, String message);

}
