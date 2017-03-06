package reactive.programming.rxJava;

import rx.Observable;

import java.util.Random;

/**
 * Created by Fisher on 2/26/2017.
 */
public class AdvancedOperators {

    public static void main(String[] args) {

//        // scan()
//        Observable<BigInteger> factorials = Observable
//                .range(2, 4)
//                // BigInteger.ONE is the initial value
//                .scan(BigInteger.ONE, (big, cur) ->
//                        big.multiply(BigInteger.valueOf(cur)));
//
//        factorials.subscribe(Utils::log);
//
//
//        // reduce() -- reduce will only take the last emission
//        Observable<BigInteger> factorials2 = Observable
//                .range(2, 4)
//                .map(BigInteger::valueOf)
//                // BigInteger.ONE is the initial value
//                .reduce(BigInteger.ONE, BigInteger::multiply);
//
//        factorials2.subscribe(Utils::log);
//
//        // reduce example 2, also just return one value but that's a collection -- output type can be changed
//        Observable<Integer> obs = Observable.range(10, 20);
//        Observable<List<Integer>> obsList = obs
//                .reduce(new ArrayList<>(), (list, item) -> {
//                    list.add(item);
//                    // list.add return a boolean instead of a list
//                    // so we have to return explicit return a list here
//                    return list;
//                });
//        obsList.subscribe(Utils::log);
//
//        // collect()
//        Observable<List<Integer>> all = Observable
//                .range(10, 20)
//                // use the same mutable accumulator, reduce() will need to return a new immutable accumulator(BigInteger, every time)
//                // collector will use side effects
//                .collect(ArrayList::new, List::add);
//
//        all.subscribe(Utils::log);
//
//        // Transforming Observable<T> into Observable<List<T>> is so
//        // common that a built-in toList() operator exists.
//        Observable<Integer> obs = Observable.range(10, 20);
//        // single() is used to assert that this observable will only emit one result
//        Observable<List<Integer>> obsToList = obs.toList().single();
//        obsToList.subscribe(Utils::log);

        // distinct()
        Observable<Integer> randomInts = Observable.create(subscriber -> {
           Random rand = new Random();
           while(!subscriber.isUnsubscribed()) {
               subscriber.onNext(rand.nextInt(1000));
           }
        });

        Observable<Integer> uniqueRandomInts = randomInts
                .distinct()
                .take(10);

        /**
         * distinctUntilChanged() will compare the previous event and current event, instead of comparing current event to all the previous events -- distinct()
         * distinctUntilChanged() must only remember the last
             seen value, as opposed to distinct(), which must keep track of all unique values
             since the beginning of the stream. This means that distinctUntilChanged() has a
             predictable, constant memory footprint, as opposed to distinct().
         */

    }

}
