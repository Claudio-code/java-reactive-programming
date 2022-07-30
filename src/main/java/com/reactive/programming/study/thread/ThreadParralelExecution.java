package com.reactive.programming.study.thread;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;

public class ThreadParralelExecution {
    public static void main(String[] args) {
        Flux.range(1, 1000)
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(v -> printThreadName("subscribe: " + v));
        

        // Get values in execution order
        var hashMap = new HashMap<String, Integer>();
        Flux.range(1, 1000)
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(v -> hashMap.put("key " + v , v));
        System.out.println(hashMap.values());

        // show list in order
        var list = hashMap.values().stream().sorted();
        list.forEach(item -> {
            System.out.println(item);
        });

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
