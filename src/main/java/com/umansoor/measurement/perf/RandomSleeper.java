package com.umansoor.measurement.perf;

import java.util.concurrent.Callable;

/**
 * A Dummy random sleeping time Task.
 * 
 * @author umermansoor
 */
public class RandomSleeper implements Callable<Long> {
    
    public RandomSleeper() {
        
    }
    
    @Override public Long call() {
        long startTime = System.currentTimeMillis();
        
        try {
            Thread.sleep((long)(Math.random() * 1000));
        } catch (InterruptedException ie) {
            Thread.interrupted();
        }
        return System.currentTimeMillis() - startTime;
        
    }
    
}
