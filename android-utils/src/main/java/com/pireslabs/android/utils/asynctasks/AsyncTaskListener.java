package com.pireslabs.android.utils.asynctasks;

public interface AsyncTaskListener {

    void beforeTaskExecution();

    void onExecution(ProgressResult progress);

    void onComplete(BasicTaskResult<?> result);

    void onError(Throwable error);

    void onCancelled();

}
