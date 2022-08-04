# About project
- Learning how use reactor pattern implementation in Java.

- The Reactor have two implementation of Publishers is Mono and Flux.
    - Mono:
        - Can send 0 or 1 item.
        - Can be followed by onComplete / onError methods.
    - Flux:
        - Can send 0 or N items.
        - Can be followed by onComplete / onError methods too.

---

### Schedulers != Parallel-execution

- All the operations are always executed in sequential.
- Data is processed one by one via 1 thread in the ThreadPool for a Subscriber.
- Schedulers.parallel() - Is a thread poll for CPU tasks. Does not mean parallel execution.

---

### Flux - create / generate

| Create                                                                                                                            | Generate                                                   |
|-----------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------|
| Accepts a `Consumer<FluxSink<T>>`                                                                                                 | Accepts a `Consumer<SynchronousSink<T>>`                   |
| Consumer is invoked only once                                                                                                     | Consumer is invoked again based on the downstream demand   |
| Consumer can emit 0..N elements immediately                                                                                       | Consumer can emit only one elements                        |
| Publisher might not be aware of downstream processing speed. So we need to provide `Overflow Strategy` as an additional parameter | Publisher produces elements based on the downstream demand |
| Thread-safe                                                                                                                       | N/A                                                        |
| `fluxSink.requestedFromDownstream()` and `fluxSink.isCancelled()`                                                                 | N/A                                                        |

---

### Schedulers
| Schedulers Method | Usage                                        |
|-------------------|----------------------------------------------|
| BoundedElastic    | Network / time-consuming calls               |
| Parallel          | CPU intensive tasks                          |
| Single            | A single dedicated thread for one-off tasks  |
| Immediate         | Current thread                               |

---

### Operators for Scheduling
| Operator    | Usage          |
|-------------|----------------|
| subscribeOn | for upstream   |
| publishOn   | for downstream |

----

### Overflow Strategy
| Strategy | Behavior                                                                    |
|----------|-----------------------------------------------------------------------------|
| Buffer   | Keep in memory                                                              |
| Drop     | Once the queue is full, new items will be dropped                           |
| Latest   | Once the queue is full, keep 1 latest item as and when it arrives, drop old |
| Error    | Throw error to the downstream                                               |

---
---

## Stack used
- Java 17 lts
- Maven

