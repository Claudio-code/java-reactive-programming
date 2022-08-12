package com.reactive.programming.study.overFlowStrategy;

import com.reactive.util.TimeUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;

public class DropAndGetAllDropedItems {
    public static void main(String[] args) {
        // if you set it value the queue support all 501 items
        // System.setProperty("reactor.bufferSize.small", "501");

        var list = new ArrayList<>();
        Flux.create(fluxsink -> {
            for (int i = 0; i < 501; i++) {
                fluxsink.next(i);
                System.out.println("Pushed: " + i);
                TimeUtil.sleepMilleSeconds(1);
            }
            fluxsink.complete();
        })
        .onBackpressureDrop(list::add)
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(i -> {
            TimeUtil.sleepMilleSeconds(10);
        })
        .subscribe(i -> System.out.println("item: " + i));

        TimeUtil.sleepSeconds(10000);
    }
}
