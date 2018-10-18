package com.pireslabs.backgroundtasks.tasks;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public final class SimpleAsyncCounterTask extends AsyncTask<Integer, Integer, Void> {

    private final WeakReference<TextView> textView;

    private final WeakReference<ProgressBar> progressBar;

    public SimpleAsyncCounterTask(TextView textView, ProgressBar progressBar) {
        this.textView = new WeakReference<>(textView);
        this.progressBar = new WeakReference<>(progressBar);
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
        this.progressBar.get().setVisibility(View.VISIBLE);
        this.changeCounterValueTo(0);
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        Integer counterSize = integers[0];
        for (int i = 0; i <= counterSize; i++) {
            publishProgress(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                changeCounterValueTo(0);
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
        this.progressBar.get().setVisibility(View.INVISIBLE);
        Toast.makeText(this.progressBar.get().getContext(), "Contagem concluída.", Toast.LENGTH_SHORT).show();
        this.changeCounterValueTo(0);
    }

}
