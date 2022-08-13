package com.reactive.programming.study.overFlowStrategy;

import com.reactive.util.TimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class LatestExample {
    public static void main(String[] args) {
        Flux.create(fluxsink -> {
            for (int i = 0; i < 501; i++) {
                fluxsink.next(i);
                printThreadName("Pushed: " + i);
                TimeUtil.sleepMilleSeconds(1);
            }
            fluxsink.complete();
        })
        .onBackpressureLatest()
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(i -> {
            TimeUtil.sleepMilleSeconds(10);
        })
        .subscribe(i -> printThreadName("item: " + i));
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread: " + Thread.currentThread().getName());
    }
}
