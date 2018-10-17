package com.pireslabs.backgroundtasks.ui.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.pireslabs.backgroundtasks.R;
import com.pireslabs.backgroundtasks.tasks.SimpleAsyncCounterTask;

public class SimpleAsyncTask extends AppCompatActivity {

    private TextView txtvwCounter;

    private Button btnStart;

    private SimpleAsyncCounterTask counterTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_async_task);
        this.initControls();
    }

    private void initControls() {
        this.txtvwCounter = findViewById(R.id.txtvw_progress_value);
        this.btnStart = findViewById(R.id.btn_start);
        this.setupStartButton();
    }

    private void setupStartButton() {
        if (this.btnStart != null) {
            this.btnStart.setOnClickListener(view -> {
                startCounterTask(35);
            });
        }
    }

    private void startCounterTask(int counterSize) {
        if ((this.counterTask != null) && (this.counterTask.getStatus().equals(AsyncTask.Status.RUNNING))) {
            this.counterTask.cancel(true);
        }
        this.counterTask = new SimpleAsyncCounterTask(this.txtvwCounter);
        this.counterTask.execute(counterSize);
    }
}
