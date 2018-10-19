package com.pireslabs.android.utils.async;

import android.os.AsyncTask;

public abstract class BasicAsyncTaskWithCallback extends AsyncTask<Void, ProgressResult<?>, BasicAsyncTaskResult<?>> {

    private final String taskTag;

    private final AsyncTaskEvents asyncTaskEvents;

    public BasicAsyncTaskWithCallback(String taskTag, AsyncTaskEvents asyncTaskEvents) {
        if (asyncTaskEvents == null) {
            throw new IllegalArgumentException("Os eventos desta AsyncTask não podem ser nulos.");
        }
        this.taskTag = taskTag;
        this.asyncTaskEvents = asyncTaskEvents;
    }

    public BasicAsyncTaskWithCallback(AsyncTaskEvents asyncTaskEvents) {
        this(null, asyncTaskEvents);
    }

    @Override
    protected void onPreExecute() {
        this.asyncTaskEvents.beforeTaskExecution();
    }

    @Override
    protected void onProgressUpdate(ProgressResult<?>... values) {
        ProgressResult<?> progressResult = values[0];
        this.asyncTaskEvents.onExecution(progressResult);
    }

    @Override
    protected void onPostExecute(BasicAsyncTaskResult<?> basicAsyncTaskResult) {
        Throwable error = basicAsyncTaskResult.getError();
        if (error != null) {
            this.asyncTaskEvents.onError(error);
        } else {
            this.asyncTaskEvents.onComplete(basicAsyncTaskResult);
        }
    }

    @Override
    protected void onCancelled() {
        this.asyncTaskEvents.onCancelled();
    }

    public boolean isRunning() {
        return (this.getStatus() == Status.RUNNING);
    }

    public void cancel() {
        if (this.isRunning()) {
            this.cancel(true);
        }
    }

    public String getTaskTag() {
        return this.taskTag;
    }

    public interface AsyncTaskEvents {
        void beforeTaskExecution();
        void onComplete(BasicAsyncTaskResult<?> result);
        void onExecution(ProgressResult<?> progress);
        void onError(Throwable error);
        void onCancelled();
    }
}
