package com.pireslabs.android.utils.asynctasks;

public abstract class BasicTaskResult<T> {

    private final String taskTag;

    private final T result;

    private final Throwable error;

    private BasicTaskResult(String taskTag, T result, Throwable error) {
        this.taskTag = taskTag;
        this.result = result;
        this.error = error;
    }

    BasicTaskResult(String taskTag, T result) {
        this(taskTag, result, null);
    }

    BasicTaskResult(T result) {
        this(null, result, null);
    }

    BasicTaskResult(String taskTag, Throwable error) {
        this(taskTag, null, error);
    }

    BasicTaskResult(Throwable error) {
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
