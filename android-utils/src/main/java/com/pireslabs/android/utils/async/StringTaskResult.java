package com.pireslabs.android.utils.async;

public final class StringTaskResult extends BasicAsyncTaskResult<String> {

    public StringTaskResult(String taskTag, String result) {
        super(taskTag, result);
    }

    public StringTaskResult(String result) {
        super(result);
    }

    public StringTaskResult(String taskTag, Throwable error) {
        super(taskTag, error);
    }

    public StringTaskResult(Throwable error) {
        super(error);
    }

}
