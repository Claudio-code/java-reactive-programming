package com.reactive.programming.study.thread;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ThreadMultipleSubscribeOn {
    public static void main(String[] args) {
        final var flux = Flux.create(fluxSink -> {
          printThreadName("create");
          fluxSink.next(1);
          fluxSink.next(2);
        })
        .subscribeOn(Schedulers.boundedElastic())
        .doOnNext(i -> printThreadName("next: " + i));

        flux
                .doFirst(() -> printThreadName("first 2"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("first 1"))
                .subscribe(v -> printThreadName("subscribe " + v));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread: " + Thread.currentThread().getName());
    }
}
