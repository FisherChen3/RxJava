package reactive.programming.rxJava;

import org.apache.commons.lang3.tuple.Pair;
import rx.Observable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Fisher on 2/26/2017.
 */
public class CombiningStreams {

    public static void main(String[] args) {

//        // concat() (instance method concatWith())
//        // emit second observable when first complete
//        Observable<Integer> ob = Observable.range(1, 100);
//        Observable.concat(
//                ob.take(5),
//                ob.takeLast(5)
//        ).forEach(Utils::log);
//
//        // fallback way of using concat()
//        /**
//         * Observables are lazy, so neither loadFromCache() nor loadFromDb() actually load
//         any data yet. loadFromCache() can complete without emitting any events when cache
//         is empty, but loadFromDb() always emits one Car. concat() followed by first() will
//         initially subscribe to fromCache and if that emits one item, concat() will not subscribe
//         to fromDb.
//         *  Observable<Car> fromCache = loadFromCache();
//         Observable<Car> fromDb = loadFromDb();
//         Observable<Car> found = Observable
//         .concat(fromCache, fromDb)
//         .first();
//         */

        // diff of concat(), merge() and swithOnNext()
        Observable<String> alice = speak(
                "To be, or not to be: that is the question", 110);
        Observable<String> bob = speak(
                "Though this be madness, yet there is method in't", 90);
        Observable<String> jane = speak("There are more things in Heaven and Earth, " +
                "Horatio, than are dreamt of in your philosophy", 100);

//        // merge()
//        Observable
//                .merge(
//                        alice.map(w -> "Alice: " + w),
//                        bob.map(w -> "Bob: " + w),
//                        jane.map(w -> "Jane: " + w)
//                )
//                .subscribe(System.out::println);

        // concat()
//        Observable
//                .concat(
//                        alice.map(v -> "Alice: " + v),
//                        bob.map(v -> "Bob: " + v),
//                        jane.map(v -> "Jane: " + v)
//                )
//                .subscribe(Utils::log);

        // switchOnNext() - page102
        /**
         * 1. swithOnNext works on Observable<Observable<T>>
         * 2. swithOnNext will discard the old stream when it works on next stream (when next shows up)
         */

        Random rnd = new Random();

        Observable<Observable<String>> quotes = Observable.just(
                alice.map(v -> "Alice: " + v),
                bob.map(v -> "Bob: " + v),
                jane.map(v -> "Jane: " + v)

        )
                .flatMap(ob -> Observable.just(ob)
                        .delay(rnd.nextInt(), TimeUnit.SECONDS));
        Observable
                .switchOnNext(quotes)
                .subscribe(Utils::log);


        Utils.pend(10000);
    }

    // exmaple from page98
    static Observable<String> speak(String quote, long millisPerChar) {
        String[] tokens = quote.replaceAll("[:,]", "").split(" ");
        Observable<Long> delayObs = Observable.from(tokens)
                .map(token -> millisPerChar * token.length())
                .scan((total, current) -> total + current);
        Observable<String> ob = Observable.from(tokens)
                // startWith(T item) Returns an Observable that emits a specified item before it begins to emit items emitted by the source
                .zipWith(delayObs.startWith(0L), Pair::of)
                .flatMap(pair -> Observable.just(pair.getLeft())
                        .delay(pair.getRight(), TimeUnit.MILLISECONDS));
        return ob;
    }

}
