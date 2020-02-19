package com.pireslabs.backgroundtasks.ui.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pireslabs.android.utils.ui.BasicActivity;
import com.pireslabs.backgroundtasks.R;
import com.pireslabs.backgroundtasks.helpers.NumbersHelpers;
import com.pireslabs.backgroundtasks.helpers.StringHelper;
import com.pireslabs.backgroundtasks.tasks.SimpleAsyncCounterTask;

public class SimpleAsyncTaskActivity extends BasicActivity {

    private TextView txtvwCounter;

    private ProgressBar progressBar;

    private Button btnStart;

    private TextView txtvwSumario;

    private SimpleAsyncCounterTask counterTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_async_task);
        this.initControls();
    }

    private void initControls() {
        this.progressBar = findViewById(R.id.prgrssbr_counter);
        this.txtvwCounter = findViewById(R.id.txtvw_progress_value);
        this.btnStart = findViewById(R.id.btn_start);
        this.txtvwSumario = findViewById(R.id.txtvw_sumario);
        this.setupStartButton();
    }

    private void setupStartButton() {
        if (this.btnStart != null) {
            this.btnStart.setOnClickListener(view -> {
                clearSummary();
                int counterSize = NumbersHelpers.getRandom(10, 35);
                addToSummary("Tamanho do contador: " + counterSize);
                startCounterTask(counterSize);
            });
        }
    }

    private void addToSummary(String message) {
        String summaryContent = this.txtvwSumario.getText().toString();
        summaryContent += message + "\n";
        this.txtvwSumario.setText(summaryContent);
    }

    private void clearSummary() {
        this.txtvwSumario.setText(StringHelper.empty());
    }

    private void startCounterTask(int counterSize) {
        if ((this.counterTask != null) && (this.counterTask.getStatus().equals(AsyncTask.Status.RUNNING))) {
            this.counterTask.cancel(true);
        }
        this.counterTask = new SimpleAsyncCounterTask(this.txtvwCounter, this.progressBar);
        this.counterTask.execute(counterSize);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ((this.counterTask != null) && (this.counterTask.getStatus().equals(AsyncTask.Status.RUNNING))) {
            this.counterTask.cancel(true);
        }
    }
}
