package com.pireslabs.backgroundtasks.tasks;

import com.pireslabs.android.utils.async.BasicAsyncTaskResult;
import com.pireslabs.android.utils.async.BasicAsyncTaskWithCallback;
import com.pireslabs.android.utils.async.IntegerTaskResult;
import com.pireslabs.android.utils.async.ProgressResult;
import com.pireslabs.android.utils.log.Log;
import com.pireslabs.backgroundtasks.services.CounterService;

public final class CounterAsyncTask extends BasicAsyncTaskWithCallback {

    private final CounterService counterService;

    public CounterAsyncTask(String taskTag, AsyncTaskEvents asyncTaskEvents, int counterSize) {
        super(taskTag, asyncTaskEvents);
        this.counterService = new CounterService(counterSize);
        Log.info(this.getTaskTag(), String.format("Minha tarefa é contar até: %s", this.counterService.getCounterSize()));
    }

    @Override
    protected BasicAsyncTaskResult<?> doInBackground(Void... voids) {
        Log.info(this.getTaskTag(), "Iniciando contagem.");
        while (this.counterService.hasNext()) {
            Log.debug(this.getTaskTag(), String.format("O contador esta em: %02d.", this.counterService.getCurrentValue()));
            publishProgress(new ProgressResult<Integer>(this.counterService.getCurrentValue()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Log.error(this.getTaskTag(), String.format("Thread interrompida devido à: %s", ex.getMessage()));
            }
        }
        Log.info(this.getTaskTag(), "Contagem encerrada.");
        return new IntegerTaskResult(this.counterService.getCurrentValue());
    }
}
