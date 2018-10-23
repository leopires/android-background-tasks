package com.pireslabs.android.utils.async;

import android.os.AsyncTask;

import com.pireslabs.android.utils.log.Log;

import java.util.ArrayList;

public abstract class BasicObservableAsyncTask extends AsyncTask<Void, ProgressResult, BasicTaskResult> {

    private final String taskTag;

    private final ArrayList<AsyncTaskListener> listeners;

    protected BasicObservableAsyncTask(String taskTag) {
        this.taskTag = taskTag;
        this.listeners = new ArrayList<>();
        Log.info(this.taskTag, "Criada com sucesso.");
    }

    public String getTaskTag() {
        return this.taskTag;
    }

    public void addListener(AsyncTaskListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("O objeto que irá acompanhar a execução da AsyncTask não pode ser null.");
        }
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    public void removeListener(AsyncTaskListener listener) {
        if (listener == null)
            return;
        this.listeners.remove(listener);
    }

    public void removeAllListeners() {
        this.listeners.clear();
    }

    public boolean isRunning() {
        return this.getStatus() == Status.RUNNING;
    }

    public void cancel() {
        if (this.isRunning()) {
            this.cancel(true);
        }
    }

    private void notifyOnBeforeTaskExecution() {
        for (AsyncTaskListener listener : this.listeners) {
            listener.beforeTaskExecution();
        }
    }

    private void notifyOnExecution(ProgressResult progressResult) {
        for (AsyncTaskListener listener : this.listeners) {
            listener.onExecution(progressResult);
        }
    }

    private void notifyOnComplete(BasicTaskResult result) {
        for (AsyncTaskListener listener: this.listeners) {
            listener.onComplete(result);
        }
    }

    private void notifyOnError(Throwable error) {
        for (AsyncTaskListener listener: this.listeners) {
            listener.onError(error);
        }
    }

    private void notifyOnCancelled() {
        for (AsyncTaskListener listener: this.listeners) {
            listener.onCancelled();
        }
    }

    @Override
    protected void onPreExecute() {
        this.notifyOnBeforeTaskExecution();
    }

    @Override
    protected void onProgressUpdate(ProgressResult... values) {
        this.notifyOnExecution(values[0]);
    }

    @Override
    protected void onPostExecute(BasicTaskResult basicTaskResult) {
        Throwable error = basicTaskResult.getError();
        if (error != null) {
            this.notifyOnError(error);
        } else {
            this.notifyOnComplete(basicTaskResult);
        }
    }

    @Override
    protected void onCancelled(BasicTaskResult result) {
        this.notifyOnCancelled();
    }
}
