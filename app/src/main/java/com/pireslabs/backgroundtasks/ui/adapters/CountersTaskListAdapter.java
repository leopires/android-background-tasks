package com.pireslabs.backgroundtasks.ui.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pireslabs.android.utils.asynctasks.AsyncTaskListener;
import com.pireslabs.android.utils.asynctasks.BasicTaskResult;
import com.pireslabs.android.utils.asynctasks.IntegerTaskResult;
import com.pireslabs.android.utils.asynctasks.ProgressResult;
import com.pireslabs.android.utils.log.Log;
import com.pireslabs.backgroundtasks.R;
import com.pireslabs.backgroundtasks.tasks.ObservableCounterAsyncTask;

public class CountersTaskListAdapter extends RecyclerView.Adapter<CountersTaskListAdapter.CounterTaskViewHolder> {

    private final ObservableCounterAsyncTask[] countersTasks;

    private final LayoutInflater layoutInflater;

    public CountersTaskListAdapter(Context context, ObservableCounterAsyncTask[] countersTasks) {
        this.countersTasks = countersTasks;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CounterTaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View itemView = this.layoutInflater.inflate(R.layout.card_view_counter_async_task, viewGroup, false);
        return new CounterTaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterTaskViewHolder viewHolder, int i) {
        final ObservableCounterAsyncTask counterAsyncTask = this.countersTasks[i];
        viewHolder.setObservableCounterTaskToObserve(counterAsyncTask);
    }

    @Override
    public void onViewRecycled(@NonNull CounterTaskViewHolder holder) {
        holder.removeObservableCounterTaskToObserve();
    }

    @Override
    public int getItemCount() {
        if (this.countersTasks == null) {
            return 0;
        }
        return this.countersTasks.length;
    }

    static final class CounterTaskViewHolder extends RecyclerView.ViewHolder implements AsyncTaskListener {

        private final Context context;

        private final ProgressBar prgrssbrCounterTaskProgress;

        private final TextView txtvwCounterTaskCurrentValue;

        private final TextView txtvwCounterTaskTag;

        private final TextView txtvwCounterTaskSize;

        private ObservableCounterAsyncTask counterTask = null;

        CounterTaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            this.prgrssbrCounterTaskProgress = itemView.findViewById(R.id.prgrssbr_counter_task_progress_animation);
            this.txtvwCounterTaskCurrentValue = itemView.findViewById(R.id.txtvw_counter_task_current_value);
            this.txtvwCounterTaskTag = itemView.findViewById(R.id.txtvw_counter_task_task_tag);
            this.txtvwCounterTaskSize = itemView.findViewById(R.id.txtvw_counter_task_task_size);
        }

        void setObservableCounterTaskToObserve(ObservableCounterAsyncTask counterAsyncTask) {
            this.counterTask = counterAsyncTask;
            this.counterTask.addListener(this);
            this.beforeTaskExecution();
            if (this.counterTask.getStatus() == AsyncTask.Status.FINISHED) {
                this.onComplete(this.counterTask.getFinalResult());
            }
        }

        void removeObservableCounterTaskToObserve() {
            if (this.counterTask != null) {
                this.counterTask.removeListener(this);
                this.counterTask = null;
            }
        }

        @Override
        public void beforeTaskExecution() {
            if (this.counterTask.isRunning())
                this.prgrssbrCounterTaskProgress.setVisibility(View.VISIBLE);
            this.txtvwCounterTaskCurrentValue.setText(String.valueOf(0));
            this.txtvwCounterTaskTag.setText(this.formatCounterTaskTag(this.counterTask.getTaskTag()));
            this.txtvwCounterTaskSize.setText(this.formatCounterTaskSize(this.counterTask.getTaskSize()));
        }

        private String formatCounterTaskTag(String taskTag) {
            return String.format(this.context.getResources().getString(R.string.counter_task_tag), taskTag);
        }

        private String formatCounterTaskSize(int taskSize) {
            return String.format(this.context.getResources().getString(R.string.counter_task_size), String.valueOf(taskSize));
        }

        @Override
        public void onExecution(ProgressResult progress) {
            ProgressResult<Integer> progressResult = (ProgressResult<Integer>) progress;
            this.txtvwCounterTaskCurrentValue.setText(String.format("%s", progressResult.getResult()));
        }

        @Override
        public void onComplete(BasicTaskResult result) {
            if (result != null) {
                this.prgrssbrCounterTaskProgress.setVisibility(View.INVISIBLE);
                this.txtvwCounterTaskCurrentValue.setText(String.format("%s", ((IntegerTaskResult)result).getResult().toString()));
            }
        }

        @Override
        public void onError(Throwable error) {
            Log.error(this.counterTask.getTaskTag(), error.getMessage());
        }

        @Override
        public void onCancelled() {
            Log.error(this.counterTask.getTaskTag(), "Cancelada.");
        }
    }
}
