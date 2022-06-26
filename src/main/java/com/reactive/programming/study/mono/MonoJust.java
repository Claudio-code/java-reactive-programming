package com.reactive.programming.study.mono;

import reactor.core.publisher.Mono;

public class MonoJust {
    public static void main(String[] args) {
        // publisher
        Mono<Integer> mono = Mono.just(1);
        System.out.println(mono);

        mono.subscribe(System.out::println);
    }
}
