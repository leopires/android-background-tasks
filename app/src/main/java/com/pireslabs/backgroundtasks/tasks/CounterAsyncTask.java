package com.pireslabs.backgroundtasks.tasks;

import com.pireslabs.android.utils.async.AsyncTaskCancelledException;
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
        Log.info(this.getTaskTag(), String.format("Olá! Minha tarefa é contar até: %s", this.counterService.getCounterSize()));
    }

    @Override
    protected BasicAsyncTaskResult<?> doInBackground(Void... voids) {
        Log.info(this.getTaskTag(), "Iniciando execução da contagem.");
        while (this.counterService.hasNext()) {
            if (isCancelled())
                break;
            Log.debug(this.getTaskTag(), String.format("O contador está em: %s.", this.counterService.getCurrentValue()));
            publishProgress(new ProgressResult<Integer>(this.counterService.getCurrentValue()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                String message =  String.format("A execução da Thread %s foi interrompida.", Thread.currentThread().getId());
                return new IntegerTaskResult(new AsyncTaskCancelledException(message));
            }
        }
        Log.info(this.getTaskTag(), "Contagem encerrada.");
        return new IntegerTaskResult(this.counterService.getCurrentValue());
    }
}
