package com.pireslabs.android.utils.async;

public final class BooleanTaskResult extends BasicTaskResult<Boolean> {

    public BooleanTaskResult(String taskTag, Boolean result) {
        super(taskTag, result);
    }

    public BooleanTaskResult(Boolean result) {
        super(result);
    }

    public BooleanTaskResult(String taskTag, Throwable error) {
        super(taskTag, error);
    }

    public BooleanTaskResult(Throwable error) {
        super(error);
    }
}
