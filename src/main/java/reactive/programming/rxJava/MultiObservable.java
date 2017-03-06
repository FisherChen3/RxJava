package reactive.programming.rxJava;

import rx.Observable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static rx.Observable.interval;

/**
 * Created by Fisher on 2/25/2017.
 */
public class MultiObservable {

    public static void main(String[] args) {
        Observable<Long> red = interval(10, MILLISECONDS);
        Observable<Long> green = interval(10, MILLISECONDS);

        // example of zip()
//        Observable.zip(red.timestamp(),green.timestamp(),(r,g)-> r.getTimestampMillis() - g.getTimestampMillis())
//                .forEach(Utils::log);


        // example of combineLatest()
        // does not distinguish between the substreams it combines
//        Observable.combineLatest(
//                interval(17, MILLISECONDS).map(x -> "S" + x),
//                interval(10, MILLISECONDS).map(x -> "F" + x),
//                (s, f) -> f + ":" + s
//        ).forEach(System.out::println);


        // example of ob1.withLastestFromm(ob2)
        // only emit when ob1 emit
//        Observable<String> fast = interval(10, MILLISECONDS).map(x -> "F" + x);
//        Observable<String> slow = interval(17, MILLISECONDS).map(x -> "S" + x);
//        slow
//                .withLatestFrom(fast, (s, f) -> s + ":" + f)
//                .forEach(System.out::println);


        // example of amb
        /**
        What amb() does is first subscribe
        to both Observables, and when it encounters the first event in the slow stream,
        it immediately unsubscribes from the fast one and forwards events from only the
        slow one
        */
        Observable.amb(
                stream(100, 17, "S"),
                stream(200, 10, "F")
        ).subscribe(Utils::log);

        Utils.pend(3000);
    }

    static Observable<String> stream(int initialDelay, int interval, String name) {
        return Observable
                .interval(initialDelay, interval, MILLISECONDS)
                .map(x -> name + x)
                .doOnSubscribe(() ->
                    Utils.log("subscribe to "+ name))
                .doOnUnsubscribe(() ->
                    Utils.log("unsubscribe from "+ name));
    }

}
