package com.reactive.programming.study.thread;

import reactor.core.publisher.Flux;

public class ThreadDemo {
    public static void main(String[] args) {
        final var flux = Flux.create(fluxSink -> {
          printThreadName("create");
          fluxSink.next(1);
          fluxSink.next(2);
        })
                .doOnNext(i -> printThreadName("next: " + i));

        flux.subscribe(v -> printThreadName("subscribe " + v));

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
