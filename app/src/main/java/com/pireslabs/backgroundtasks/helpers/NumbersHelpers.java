package com.pireslabs.backgroundtasks.helpers;

import java.util.Random;

public final class NumbersHelpers {

    private NumbersHelpers() {}

    public static int getRandom(int min, int max) {
        Random generator = new Random();
        int number = 0;
        do {
            number = generator.nextInt(max);
        } while (number < min);
        return number;
    }

}
