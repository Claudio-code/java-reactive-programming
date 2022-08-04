package com.reactive.overFlowStrategy;

import com.reactive.util.TimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BufferExample {
    public static void main(String[] args) {
        Flux.create(fluxsink -> {
            for (int i = 0; i < 501; i++) {
                fluxsink.next(i);
                System.out.println("Pushed: " + i);
            }
            fluxsink.complete();
        })
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(i -> {
            TimeUtil.sleepMilleSeconds(10);
        })
        .subscribe(i -> System.out.println("item: " + i));

        TimeUtil.sleepSeconds(10000);
    }
}
