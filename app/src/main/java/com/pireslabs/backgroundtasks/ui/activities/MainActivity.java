package com.pireslabs.backgroundtasks.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pireslabs.backgroundtasks.R;
import com.pireslabs.backgroundtasks.ui.AppActivities;
import com.pireslabs.backgroundtasks.ui.adapters.MainOptionsAdapter;

public class MainActivity extends AppCompatActivity {

    private static final MainOptionsAdapter.ItemOption[] OPTIONS = {
            new MainOptionsAdapter.ItemOption(AppActivities.SimpleThread),
            new MainOptionsAdapter.ItemOption(AppActivities.SimpleAsyncTask),
            new MainOptionsAdapter.ItemOption(AppActivities.CounterAsyncTask)
    };

    private RecyclerView rcclrvwMainOptions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initControls();
    }

    private void initControls() {
        this.rcclrvwMainOptions = findViewById(R.id.rcclrvw_main_options);
        this.setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (this.rcclrvwMainOptions != null) {
            this.rcclrvwMainOptions.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            this.rcclrvwMainOptions.setAdapter(new MainOptionsAdapter(getBaseContext(), OPTIONS));
        }
    }
}
