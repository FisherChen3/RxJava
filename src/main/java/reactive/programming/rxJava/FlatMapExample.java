package reactive.programming.rxJava;

import rx.Observable;

/**
 * Created by Fisher on 2/25/2017.
 */
public class FlatMapExample {

    public static void main(String[] args) {
        // product (1,2,...8) with (a,b,c,d...h)  => (a1,a2,a3...b1,b2,b3....h7,h8)
        Observable<Integer> oneToEight = Observable.range(1,8);
        Observable<String> ranks = oneToEight
                .map(Object::toString);
        Observable<String> files = oneToEight
                .map(x -> 'a' + x - 1)
                .map(ascii -> (char)ascii.intValue())
                .map(ch -> Character.toString(ch));

        Observable<String> squares = files
                .flatMap(file -> ranks.map(rank -> file + rank));

        squares.subscribe(System.out::println);
    }

}
