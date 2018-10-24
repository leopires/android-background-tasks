package com.pireslabs.backgroundtasks.ui.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.pireslabs.android.utils.ui.BasicActivity;
import com.pireslabs.backgroundtasks.R;
import com.pireslabs.backgroundtasks.helpers.NumbersHelpers;
import com.pireslabs.backgroundtasks.tasks.ObservableCounterAsyncTask;
import com.pireslabs.backgroundtasks.ui.adapters.CountersTaskListAdapter;

import java.util.concurrent.Executor;

public class CounterAsyncTaskActivity extends BasicActivity {

    private static final ObservableCounterAsyncTask[] COUNTERS = new ObservableCounterAsyncTask[12];

    private Button btnStartCounters;

    private RecyclerView listaContadores;

    private Switch swtchSerialExecution;

    private boolean serialExecution = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_async_task);
        this.initControls();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (ObservableCounterAsyncTask task: COUNTERS) {
            task.cancel();
        }
    }

    private void initControls() {
        this.btnStartCounters = findViewById(R.id.btn_start_counters);
        this.listaContadores = findViewById(R.id.rcclrvw_counter_async_tasks);
        this.swtchSerialExecution = findViewById(R.id.swtch_execution_mode);
        this.setupRecyclerView();
        this.setupStartButton();
        this.setupSwitch();
    }

    private void setupRecyclerView() {
        if (this.listaContadores != null) {
            this.createTasks();
            this.listaContadores.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            this.listaContadores.setAdapter(new CountersTaskListAdapter(getBaseContext(), COUNTERS));
        }
    }

    private void createTasks() {
        for (int i = 0; i < COUNTERS.length; i++) {
            String taskTag = String.format("TSK-%s", i+1);
            COUNTERS[i] = new ObservableCounterAsyncTask(taskTag, NumbersHelpers.getRandom(10, 30));
        }
    }

    private void setupStartButton() {
        if (this.btnStartCounters != null) {
            this.btnStartCounters.setOnClickListener(view -> {
                Executor executor;
                String mensagem;
                if (serialExecution) {
                    executor = AsyncTask.SERIAL_EXECUTOR;
                    mensagem = "A execução dos contadores ocorrerá de forma sequencial.";

                } else {
                    executor = AsyncTask.THREAD_POOL_EXECUTOR;
                    mensagem = "A execução dos contadores ocorrerá de forma paralela, respeitando o limite do Pool de Execução.";
                }
                Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_LONG).show();
                for (ObservableCounterAsyncTask COUNTER : COUNTERS) {
                    COUNTER.executeOnExecutor(executor);
                }
                this.btnStartCounters.setEnabled(false);
                this.btnStartCounters.setAlpha(0.8f);
                this.swtchSerialExecution.setEnabled(false);
                this.swtchSerialExecution.setAlpha(0.8f);
            });
        }
    }

    private void setupSwitch() {
        if (this.swtchSerialExecution != null) {
            this.swtchSerialExecution.setOnCheckedChangeListener((buttonView, isChecked) -> serialExecution = isChecked);
        }
    }

}
