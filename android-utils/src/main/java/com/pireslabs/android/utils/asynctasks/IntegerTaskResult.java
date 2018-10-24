package com.pireslabs.android.utils.asynctasks;

public final class IntegerTaskResult extends BasicTaskResult<Integer> {

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
