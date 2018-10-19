package com.pireslabs.android.utils.async;

public abstract class BasicAsyncTaskResult<T> {

    private final String taskTag;

    private final T result;

    private final Throwable error;

    private BasicAsyncTaskResult(String taskTag, T result, Throwable error) {
        this.taskTag = taskTag;
        this.result = result;
        this.error = error;
    }

    public BasicAsyncTaskResult(String taskTag, T result) {
        this(taskTag, result, null);
    }

    public BasicAsyncTaskResult(T result) {
        this(null, result, null);
    }

    public BasicAsyncTaskResult(String taskTag, Throwable error) {
        this(taskTag, null, error);
    }

    public BasicAsyncTaskResult(Throwable error) {
        this(null, null, error);
    }

    public String getTaskTag() {
        return taskTag;
    }

    public T getResult() {
        return result;
    }

    public Throwable getError() {
        return error;
    }
}
