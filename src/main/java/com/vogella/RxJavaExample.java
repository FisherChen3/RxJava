package com.vogella;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Fisher on 1/31/2017.
 */
public class RxJavaExample {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Android", "Ubuntu", "Mac OS");
        Observable<List<String>> listObservable = Observable.just(list);
        listObservable.subscribe(new Observer<List<String>>() {

            @Override
            public void onComplete() {}

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(List<String> list) {
                System.out.println(list);
            }

            @Override
            public void onSubscribe(Disposable disposable) {

            }
        });
    }
}