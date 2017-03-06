package reactive.programming.rxJava;

import rx.Observable;

/**
 * Created by Fisher on 2/26/2017.
 */
public class SlicingDicing {

    public static void main(String[] args) {
//        // take() and skip()
//        Observable.range(1,5).take(3).forEach(Utils::log);
//        Observable.range(1,5).skip(3).forEach(Utils::log);
//        Observable.range(1,5).skip(5).forEach(Utils::log);
//
//        // takeLast() and skipLast()
//        Observable.range(1,5).takeLast(2).forEach(Utils::log);
//        Observable.range(1,5).skipLast(2).forEach(Utils::log);
//
//        // first() and last() == take(1).single() and takeLast(1).single()
//
//        // takeFirst(predicate) == filter(predicate).take(1).single()
//
//        // takeUtil(predicate) and takeWhile(predicate)
//        Observable.range(1,5).takeUntil(x -> x==3).forEach(Utils::log);
//        Observable.range(1,5).takeWhile(x -> x!=3).forEach(Utils::log);
//
//        /**
//         * elementAt(n)  -> Extracting a specific item by index
//         */
//
//        /**
//         * …OrDefault() operators  ->  elementAtOrDefault(), firstOrDefault(), lastOrDefault(), and singleOrDefault().
//         */
//
//        // count()
//        Observable.just('A','B','C','D').reduce(0,(sizeSoFar,ch) -> sizeSoFar + 1).forEach(Utils::log);
//        // it's same by using count()
//        final Observable<Integer> count = Observable.just('A', 'B', 'C', 'D').count();
//        count.forEach(Utils::log);

        // all(predicate),  exists(predicate), and contains(value)  -> return Observable<Boolean>
        Observable<Integer> numbers = Observable.range(1,5);
        numbers.all(x -> x!=4).forEach(Utils::log);
        numbers.exists(x -> x==4).forEach(Utils::log);
        numbers.contains(4).forEach(Utils::log);


        Utils.pend(3000);
    }

}
