package reactive.programming.rxJava;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.concurrent.*;

/**
 * Created by Fisher on 3/4/2017.
 */
public class MultiThread {

    public static void main(String[] args) {

//        ThreadFactory threadFactory = new ThreadFactoryBuilder()
//                .setNameFormat("MyPool-%d")
//                .build();
//
//        Executor poolA = new ThreadPoolExecutor(
//                10, //corePoolSize
//                10, //maximumPoolSize
//                0L, TimeUnit.MILLISECONDS, //keepAliveTime, unit
//                new LinkedBlockingQueue<>(1000), //workQueue
//                threadFactory
//        );

        ExecutorService poolA = Executors.newFixedThreadPool(10, threadFactory("Sched-A-%d"));

        Scheduler schedulerA = Schedulers.from(poolA);

//        Utils.log("Starting");
//        final Observable<String> obs = simple();
//        Utils.log("Created");
//        obs
//                .subscribeOn(schedulerA)
//                .subscribe(
//                        x -> Utils.log("Got " + x),
//                        Throwable::printStackTrace,
//                        () -> Utils.log("Completed")
//                );
//        Utils.log("Exiting");


        Utils.log("Starting");
        final Observable<String> obs = simple();
        Utils.log("Created");
        obs
                .doOnNext(Utils::log)
                .map(x -> x + '1')
                .doOnNext(Utils::log)
                .map(x -> x + '2')
                .subscribeOn(schedulerA)
                .doOnNext(Utils::log)
                .subscribe(
                        x -> Utils.log("Got " + x),
                        Throwable::printStackTrace,
                        () -> Utils.log("Completed")
                );
        Utils.log("Exiting");

        Observable.just('A', 'B')
                .delay(3,TimeUnit.SECONDS,schedulerA)
                .subscribe(Utils::log);
    }


    static Observable<String> simple() {
        return Observable.create(subscriber -> {
            Utils.log("Subscribed");
            subscriber.onNext("A");
            subscriber.onNext("B");
            subscriber.onCompleted();
        });
    }

    private static ThreadFactory threadFactory(String pattern) {
        return new ThreadFactoryBuilder()
                .setNameFormat(pattern)
                .build();
    }
}


