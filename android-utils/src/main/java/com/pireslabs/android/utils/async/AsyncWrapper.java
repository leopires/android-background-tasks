package com.pireslabs.android.utils.async;

import android.os.AsyncTask;

public class AsyncWrapper extends AsyncTask<Void, Void, Void> {

    private AsyncEvents events;

    private AsyncWrapper nextTask;

    public AsyncWrapper(AsyncEvents events) {
        this.events = events;
    }

    public void setNextTask(AsyncWrapper nextTask) {
        this.nextTask = nextTask;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        this.events.onBackgroung();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        this.events.onComplete();
        if(nextTask != null) {
            this.nextTask.executeOnExecutor(THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    protected void onCancelled() {
        this.events.onCancelled();
    }

    public interface AsyncEvents {
        void onBackgroung();
        void onComplete();
        void onCancelled();
    }
}
