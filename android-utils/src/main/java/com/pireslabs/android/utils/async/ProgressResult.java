package com.pireslabs.android.utils.async;

public final class ProgressResult<T> {

    private final T result;

    public ProgressResult(T result) {
        this.result = result;
    }

    public T getResult() {
        return this.result;
    }

}
