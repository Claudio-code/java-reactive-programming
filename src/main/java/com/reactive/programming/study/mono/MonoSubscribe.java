package com.reactive.programming.study.mono;

import reactor.core.publisher.Mono;

public class MonoSubscribe {
    public static void main(String[] args) {
        var monoString = Mono.just("ok go to next");
        monoString.subscribe(
                item -> System.out.println(item.toLowerCase()),
                throwable -> System.out.println(throwable.getMessage()),
                () -> System.out.println("finished finally")
        );

        // Throw example in subscribe
        var monoThrow = Mono.just("dw")
                .map(String::length)
                .map(l -> l / 0);
        monoThrow.subscribe(
                item -> System.out.println(item),
                throwable -> System.out.println(throwable.getMessage()),
                () -> System.out.println("finished finally 2")
        );
    }
}
