package com.vogella;



import rx.Observable;
import rx.Observer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Fisher on 1/31/2017.
 */
public class RxJavaExample1 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Android", "Ubuntu", "Mac OS");
        Observable<List<String>> listObservable = Observable.just(list);
        listObservable.subscribe(new Observer<List<String>>() {

            @Override
            public void onCompleted() {

                System.out.println("completed");

            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(List<String> list) {
                System.out.println(list);
            }

        });
    }
}
