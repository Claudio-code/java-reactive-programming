package com.reactive.programming.study.mono;

import com.github.javafaker.Faker;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class NoBlockingExample {
    public static void main(String[] args) {
        getName()
                .subscribeOn(Schedulers.parallel())
                .subscribe(s -> System.out.println(s));
        getName()
                .subscribeOn(Schedulers.parallel())
                .subscribe(s -> System.out.println(s));
        getName()
                .subscribeOn(Schedulers.parallel())
                .subscribe(s -> System.out.println(s));
        getName()
                .subscribeOn(Schedulers.parallel())
                .subscribe(s -> System.out.println(s));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Mono<String> getName()  {
        System.out.println("Entred getName method");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Faker.instance().name().fullName();
        });
    }
}
