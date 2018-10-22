package com.pireslabs.backgroundtasks.tasks;

import com.pireslabs.android.utils.async.AsyncTaskCancelledException;
import com.pireslabs.android.utils.async.BasicObservableAsyncTask;
import com.pireslabs.android.utils.async.BasicTaskResult;
import com.pireslabs.android.utils.async.IntegerTaskResult;
import com.pireslabs.android.utils.async.ProgressResult;
import com.pireslabs.android.utils.log.Log;
import com.pireslabs.backgroundtasks.services.CounterService;

public final class ObservableCounterAsyncTask extends BasicObservableAsyncTask {

    private final CounterService counterService;

    private IntegerTaskResult taskResult;

    public ObservableCounterAsyncTask(String taskTag, int counterSize) {
        super(taskTag);
        this.counterService = new CounterService(counterSize);
        Log.info(this.getTaskTag(), String.format("Olá! Meu objetivo é contar até: %s", counterSize));
    }

    public int getTaskSize() {
        return this.counterService.getCounterSize();
    }

    public int getTaskCurrentPosition() {
        return this.counterService.getCurrentValue();
    }

    @Override
    protected BasicTaskResult doInBackground(Void... voids) {
        Log.info(this.getTaskTag(), "Iniciando execução da contagem.");
        while (this.counterService.hasNext()) {
            Log.debug(this.getTaskTag(), String.format("O valor atual do meu contador é: %s de %s.",
                    this.counterService.getCurrentValue(), this.counterService.getCounterSize()));
            publishProgress(new ProgressResult<>(this.counterService.getCurrentValue()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                String message = String.format("Foi solicitado que a Thread [ %s | %s ] interrompa suas atividades.",
                        Thread.currentThread().getId(),
                        Thread.currentThread().getName());
                this.taskResult = new IntegerTaskResult(new AsyncTaskCancelledException(message));
                return this.taskResult;
            }
        }
        Log.info(this.getTaskTag(), "Contagem concluída.");
        this.taskResult = new IntegerTaskResult(this.counterService.getCurrentValue());
        return this.taskResult;
    }

    public BasicTaskResult getFinalResult() {
        return this.taskResult;
    }
}
