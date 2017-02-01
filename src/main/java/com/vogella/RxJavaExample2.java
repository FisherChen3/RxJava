package com.vogella;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Fisher on 1/31/2017.
 */
public class RxJavaExample2 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","stream","not");
        Observable.from(list)
                    .filter(s->s.contains("e"))
                    .map(String::toUpperCase)
                    .reduce(new StringBuilder(), StringBuilder::append)
                    .subscribe(System.out::println,System.out::println,System.out::println);
    }

}
