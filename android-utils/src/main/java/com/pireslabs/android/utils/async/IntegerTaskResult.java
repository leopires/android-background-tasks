package com.pireslabs.android.utils.async;

public final class IntegerTaskResult extends BasicAsyncTaskResult<Integer> {

    public IntegerTaskResult(String taskTag, Integer result) {
        super(taskTag, result);
    }

    public IntegerTaskResult(Integer result) {
        super(result);
    }

    public IntegerTaskResult(String taskTag, Throwable error) {
        super(taskTag, error);
    }

    public IntegerTaskResult(Throwable error) {
        super(error);
    }
}
