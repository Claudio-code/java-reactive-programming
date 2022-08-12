package com.reactive.programming.study.overFlowStrategy;

import com.reactive.util.TimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class DropOneExample {
    public static void main(String[] args) {
        // Reactor queue have default size 256 items, when reaches it size queue drop all items and send new items.
        // It example I set queue size in 6
        System.setProperty("reactor.bufferSize.small", "6");

        Flux.create(fluxsink -> {
            for (int i = 0; i < 501; i++) {
                fluxsink.next(i);
                System.out.println("Pushed: " + i);
                TimeUtil.sleepMilleSeconds(1);
            }
            fluxsink.complete();
        })
        .onBackpressureDrop()
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(i -> {
            TimeUtil.sleepMilleSeconds(10);
        })
        .subscribe(i -> System.out.println("item: " + i));

        TimeUtil.sleepSeconds(10000);
    }
}
