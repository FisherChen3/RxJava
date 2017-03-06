package reactive.programming.rxJava;

import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.Date;

/**
 * Created by Fisher on 3/4/2017.
 */
public class SchedulerExample {

    static long start;

    public static void main(String[] args) {
        start = new Date().getTime();

//        Scheduler scheduler = Schedulers.immediate();
        Scheduler scheduler = Schedulers.trampoline();
        Scheduler.Worker worker = scheduler.createWorker();

        log("Main start");
        worker.schedule(() -> {
            log(" Outer start");
            sleepOneSecond();
            worker.schedule(() -> {
                log(" Inner start");
                sleepOneSecond();
                log(" Inner end");
            });
            log(" Outer end");
        });
        log("Main end");
        worker.unsubscribe();
    }


    static void log(Object label) {
        System.out.println(
                System.currentTimeMillis() - start + "\t| " +
                        Thread.currentThread().getName() + "\t| " +
                        label);
    }

    static void sleepOneSecond(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
