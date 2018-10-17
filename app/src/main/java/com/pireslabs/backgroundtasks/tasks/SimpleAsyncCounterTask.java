package com.pireslabs.backgroundtasks.tasks;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public final class SimpleAsyncCounterTask extends AsyncTask<Integer, Integer, Void> {

    private final WeakReference<TextView> textView;

    public SimpleAsyncCounterTask(TextView textView) {
        this.textView = new WeakReference<>(textView);
    }

    private void changeCounterValueTo(int counterValue) {
        TextView textView = this.textView.get();
        if (textView != null) {
            textView.setText(String.valueOf(counterValue));
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.changeCounterValueTo(0);
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        Integer counterSize = integers[0];
        for (int i = 0; i <= counterSize; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        this.changeCounterValueTo(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
    }

}
