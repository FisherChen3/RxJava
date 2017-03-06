package reactive.programming.rxJava;

import org.apache.commons.lang3.tuple.Pair;
import rx.Observable;

/**
 * Created by Fisher on 2/26/2017.
 */
public class CustomOperators {

    public static void main(String[] args) {
//        Observable<Boolean> trueFalse = Observable.just(true, false).repeat();
//
//        Observable<Integer> ob = Observable.range(1, 20);
//
//        Observable<Integer> even = ob
//                .zipWith(trueFalse, Pair::of)
//                .filter(pair -> !pair.getRight())
//                .map(pair -> pair.getLeft());
//
////        even.forEach(Utils::log);
//
//
//        final Observable<?> observable = ob
//                .zipWith(trueFalse, (t, bool) ->
//                        bool ? Observable.just(t) : Observable.empty())
//                .flatMap(t -> t);


        //[A, B, C, D, E...]
        Observable<Character> alphabet =
                Observable
                        .range(0, 'Z' - 'A' + 1)
                        .map(c -> (char) ('A' + c));
        //[A, C, E, G, I...]
        alphabet
                .compose(odd())
                .forEach(Utils::log);

        Utils.pend(5000);
    }

    // get odd elements
    static <T> Observable<T> odd(Observable<T> upstream) {
        Observable<Boolean> trueFalse = Observable.just(true, false).repeat();
        return upstream
                .zipWith(trueFalse, Pair::of)
                .filter(Pair::getRight)
                .map(Pair::getLeft);
    }

    private static <T> Observable.Transformer<T, T> odd() {
        Observable<Boolean> trueFalse = Observable.just(true, false).repeat();
        return upstream -> upstream
                .zipWith(trueFalse, Pair::of)
                .filter(Pair::getRight)
                .map(Pair::getLeft);
    }
}
