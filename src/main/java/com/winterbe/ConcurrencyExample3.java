package com.winterbe;

import java.util.concurrent.*;

/**
 * Created by Fisher on 2/1/2017.
 */
// callable and future
public class ConcurrencyExample3 {

    public static void main(String[] args) {
        Callable<Integer> c = ()->{

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 123;
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(c);

        System.out.println("future is done? " + future.isDone());
        Integer result=0;


        try {
            System.out.println("future isDone will not block, future.get() will block");
            result = future.get(1,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println("future is done? " + future.isDone());
        System.out.println("result "+ result);


    }

}
