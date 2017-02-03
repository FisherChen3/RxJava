package com.winterbe;

import java.util.concurrent.TimeUnit;

/**
 * Created by Fisher on 2/1/2017.
 */
public class ConcurrencyExample1 {

    public static void main(String[] args) {
        Runnable r = ()->{

            try {
                String name = Thread.currentThread().getName();
                System.out.println("foo "+name);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("bar "+name);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        };

//        r.run();

        Thread t = new Thread(r);
        t.start();

        System.out.println("done!");
    }

}
