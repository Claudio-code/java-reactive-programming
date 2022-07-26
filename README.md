# Sobre o projeto
- Aprendendo a usar a implementação do padrão reactor do Java.

- O Reactor tem duas implementações de Publishers o Mono e o Flux.
    - Mono:
        - Pode emitir 0 ou 1 item.
        - Pode ser seguido pelos metodos de onComplete / onError.
    - Flux:
        - Pode emititr 0 ou N itens.
        - Pode ser seguido pelos metodos de onComplete / onError.

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

---
---

## Stack usada
- Java 17 lts
- Maven

