package com.pireslabs.android.utils.async;

public interface AsyncTaskListener {

    void beforeTaskExecution();

    void onExecution(ProgressResult progress);

    void onComplete(BasicTaskResult<?> result);

    void onError(Throwable error);

    void onCancelled();

}
