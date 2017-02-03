package com.winterbe;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Fisher on 2/1/2017.
 */

// batch invocation
public class ConcurrencyExample4 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<String>> callables = Arrays.asList(
                ()-> "task1",
                ()-> "task2",
                ()-> "task3"

        );

        try {
            executor.invokeAll(callables)
                        .stream()
                        .map(future->{
                            String str = null;
                            try {
                                str =  future.get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            return str;
                        })
                        .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
