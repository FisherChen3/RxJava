package reactive.programming.rxJava;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by Fisher on 3/5/2017.
 */
public class SampleOperator {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Observable
                .interval(7, TimeUnit.MILLISECONDS)
                .timestamp()
                .sample(1, TimeUnit.SECONDS)
                .map(ts -> ts.getTimestampMillis() - startTime + "ms: " + ts.getValue())
                .take(5)
                .subscribe(Utils::log);


        Utils.pend(10000);
    }

}
