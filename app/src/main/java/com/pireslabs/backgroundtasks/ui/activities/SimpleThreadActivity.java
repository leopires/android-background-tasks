package com.pireslabs.backgroundtasks.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pireslabs.android.utils.log.Log;
import com.pireslabs.backgroundtasks.R;
import com.pireslabs.backgroundtasks.helpers.NumbersHelpers;

public class SimpleThreadActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private TextView txtvwCounterValue;

    private Button btnStartCounter;

    private TextView txtvwAbout;

    private Thread counterThread;

    private volatile boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_thread);
        this.initControls();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ((counterThread != null) && (counterThread.isAlive())) {
            counterThread.interrupt();
        }
        counterThread = null;
    }

    private void stopThread() {
        this.running = false;
    }

    private void initControls() {
        this.progressBar = findViewById(R.id.prgrssbr_counter);
        this.txtvwCounterValue = findViewById(R.id.txtvw_progress_value);
        this.btnStartCounter = findViewById(R.id.btn_start);
        this.txtvwAbout = findViewById(R.id.txtvw_about);

        if (this.txtvwAbout != null) {
            this.txtvwAbout.setVisibility(View.INVISIBLE);
        }

        if (this.progressBar != null) {
            this.progressBar.setVisibility(View.INVISIBLE);
        }

        if (this.btnStartCounter != null) {
            this.btnStartCounter.setOnClickListener(view -> {
                if ((counterThread != null) && (counterThread.isAlive())) {
                    counterThread.interrupt();
                }
                final int counterSize = NumbersHelpers.getRandom(10, 35);
                this.txtvwAbout.setVisibility(View.VISIBLE);
                txtvwAbout.setText(String.format(getBaseContext().getResources().getString(R.string.sobre),
                        String.valueOf(counterSize)));
                progressBar.setVisibility(View.VISIBLE);
                counterThread = new Thread(() -> {
                    try {
                        Log.info(this.getClass().getSimpleName(), String.format("Iniciando contagem até: %s", counterSize));
                        for (int i = 1; i <= counterSize; i++) {
                            Log.info(this.getClass().getSimpleName(), String.format("Contador: %s", i));
                            final int counterCurrentValue = i;
                            txtvwCounterValue.post(() -> {
                                Log.info(this.getClass().getSimpleName(), "Atualizando a View.");
                                txtvwCounterValue.setText(String.format("%s", counterCurrentValue));
                                if (counterCurrentValue == counterSize) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            });
                            Thread.sleep(1000);
                            if (!running)
                                break;
                        }
                        Log.info(this.getClass().getSimpleName(), "Fim da contagem.");
                    } catch (InterruptedException ex) {
                        Log.error(this.getClass().getSimpleName(), "Thread encerrada.");
                        stopThread();
                    }
                });
                counterThread.start();
            });
        }
    }
}
