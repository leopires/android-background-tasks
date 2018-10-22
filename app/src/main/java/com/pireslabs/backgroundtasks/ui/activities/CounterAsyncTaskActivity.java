package com.pireslabs.backgroundtasks.ui.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pireslabs.android.utils.ui.BasicActivity;
import com.pireslabs.backgroundtasks.R;
import com.pireslabs.backgroundtasks.helpers.NumbersHelpers;
import com.pireslabs.backgroundtasks.tasks.ObservableCounterAsyncTask;
import com.pireslabs.backgroundtasks.ui.adapters.CountersTaskListAdapter;

public class CounterAsyncTaskActivity extends BasicActivity {

    private static final ObservableCounterAsyncTask[] COUNTERS = new ObservableCounterAsyncTask[10];

    private RecyclerView listaContadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_async_task);
        this.initControls();
    }

    private void initControls() {
        this.listaContadores = findViewById(R.id.rcclrvw_counter_async_tasks);
        this.setupRecyclerView();
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
            COUNTERS[i] = new ObservableCounterAsyncTask(taskTag, NumbersHelpers.getRandom(10, 35));
            COUNTERS[i].executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }
}
