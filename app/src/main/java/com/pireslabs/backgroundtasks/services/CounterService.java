package com.pireslabs.backgroundtasks.services;

public final class CounterService {

    private final int counterSize;

    private int currentValue;

    public CounterService(int size) {
        this.counterSize = size;
        this.currentValue = 0;
    }

    public int getCurrentValue() {
        return this.currentValue;
    }

    public int getCounterSize() {
        return this.counterSize;
    }

    public boolean hasNext() {
        if (this.currentValue < this.counterSize) {
            this.incrementCurrentValue();
            return true;
        } else {
            return false;
        }
    }

    private void incrementCurrentValue() {
        this.currentValue++;
    }
}
