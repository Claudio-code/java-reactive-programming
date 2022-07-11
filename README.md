# Sobre o projeto
- Aprendendo a usar a implementação do padrão reactor do Java.

- O Reactor tem duas implementações de Publishers o Mono e o Flux.
    - Mono:
        - Pode emitir 0 ou 1 item.
        - Pode ser seguido pelos metodos de onComplete / onError.
    - Flux:
        - Pode emititr 0 ou N itens.
        - Pode ser seguido pelos metodos de onComplete / onError.


### Flux - create / generate

| Create                                                                                                                            | Generate                                                   |
|-----------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------|
| Accepts a `Consumer<FluxSink<T>>`                                                                                                 | Accepts a `Consumer<SynchronousSink<T>>`                   |
| Consumer is invoked only once                                                                                                     | Consumer is invoked again based on the downstream demand   |
| Consumer can emit 0..N elements immediately                                                                                       | Consumer can emit only one elements                        |
| Publisher might not be aware of downstream processing speed. So we need to provide `Overflow Strategy` as an additional parameter | Publisher produces elements based on the downstream demand |
| Thread-safe                                                                                                                       | N/A                                                        |
| `fluxSink.requestedFromDownstream()` and `fluxSink.isCancelled()`                                                                 | N/A                                                        |

## Stack usada
- Java 17 lts
- Maven

