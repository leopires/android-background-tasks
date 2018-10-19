package com.pireslabs.backgroundtasks.ui.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pireslabs.android.utils.async.BasicAsyncTaskResult;
import com.pireslabs.android.utils.async.BasicAsyncTaskWithCallback;
import com.pireslabs.android.utils.async.ProgressResult;
import com.pireslabs.android.utils.log.Log;
import com.pireslabs.android.utils.ui.BasicActivity;
import com.pireslabs.backgroundtasks.R;
import com.pireslabs.backgroundtasks.helpers.NumbersHelpers;
import com.pireslabs.backgroundtasks.helpers.StringHelper;
import com.pireslabs.backgroundtasks.tasks.CounterAsyncTask;
import com.pireslabs.backgroundtasks.tasks.SimpleAsyncCounterTask;

public class CounterAsyncTaskActivity extends BasicActivity {

    private TextView txtvwCounter;

    private ProgressBar progressBar;

    private Button btnStart;

    private TextView txtvwSumario;

    private CounterAsyncTask counterTask;

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
        if ((this.counterTask != null) && (this.counterTask.isRunning())) {
            this.counterTask.cancel();
        }
        this.txtvwSumario.setText(String.format(getResources().getString(R.string.counter_size_message),
                String.valueOf(counterSize)));
        this.counterTask = (CounterAsyncTask) new CounterAsyncTask("Task 0343",
                new BasicAsyncTaskWithCallback.AsyncTaskEvents() {
                    @Override
                    public void beforeTaskExecution() {
                        progressBar.setVisibility(View.VISIBLE);
                        txtvwCounter.setText("0");
                    }

                    @Override
                    public void onComplete(BasicAsyncTaskResult result) {
                        Toast.makeText(getBaseContext(), "Contagem concluída.", Toast.LENGTH_SHORT).show();
                        txtvwCounter.setText("0");
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onExecution(ProgressResult progress) {
                        String currentCountPosition = ((ProgressResult<Integer>) progress).getResult().toString();
                        txtvwCounter.setText(currentCountPosition);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.error(getTag(), error.getMessage());
                        Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled() {
                        Toast.makeText(getBaseContext(), "Contagem cancelada.", Toast.LENGTH_SHORT).show();
                    }
                }, counterSize).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ((this.counterTask != null) && (this.counterTask.isRunning())) {
            this.counterTask.cancel();
        }
    }
}
