package com.pireslabs.backgroundtasks.ui;

import com.pireslabs.backgroundtasks.R;
import com.pireslabs.backgroundtasks.ui.activities.CounterAsyncTaskActivity;
import com.pireslabs.backgroundtasks.ui.activities.SimpleAsyncTaskActivity;
import com.pireslabs.backgroundtasks.ui.activities.SimpleThreadActivity;

public enum AppActivities {

    SimpleAsyncTask("Simple Async Task", R.drawable.ic_simple_async_task, SimpleAsyncTaskActivity.class),
    CounterAsyncTask("Counter Async Task", R.drawable.ic_opt_timer, CounterAsyncTaskActivity.class),
    SimpleThread("Simple Thread", R.drawable.ic_simple_async_task, SimpleThreadActivity.class);

    private String activityDescription;

    private int iconRes;

    private Class activityClass;

    AppActivities(String activityDescription, int iconRes, Class activityClass) {
        this.activityDescription = activityDescription;
        this.iconRes = iconRes;
        this.activityClass = activityClass;
    }

    public String getActivityDescription() {
        return this.activityDescription;
    }

    public int getIconRes() {
        return this.iconRes;
    }

    public Class getActivityClass() {
        return this.activityClass;
    }
}
