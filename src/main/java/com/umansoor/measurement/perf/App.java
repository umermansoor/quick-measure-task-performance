package com.umansoor.measurement.perf;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * Quick Code to Measure Callable Performance using CompletionService
 *
 */
public class App {

    public static void main(String[] args) throws Exception {
        int numberOfTimesToLoop = 10;

        if (args.length > 1) {
            numberOfTimesToLoop = Integer.parseInt(args[0]);
        }

        long[] executionTimes = new long[numberOfTimesToLoop];

        ExecutorService es = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<Long> completionService = new ExecutorCompletionService(es);
        for (int i = numberOfTimesToLoop; i > 0; i--) {
            completionService.submit(new RandomSleeper());
        }

        for (int i = 0; i < numberOfTimesToLoop; i++) {
            executionTimes[0] = completionService.take().get();
        }
        es.shutdown();

        Arrays.sort(executionTimes);

        System.out.println("Best time was " + executionTimes[0] + " milliseconds");
        System.out.println("Worst time was " + executionTimes[executionTimes.length - 1] + " milliseconds");
        
        for (double percent : new double[]{50, 90, 99, 99.9, 99.99}) {
            long timeInMilliseconds = executionTimes[((int) (numberOfTimesToLoop * percent / 100))];
            System.out.println(percent + "% took " + timeInMilliseconds + " milliseconds");
        }
        
        
    }
}
