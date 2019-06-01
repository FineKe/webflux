package com.fine.webfluxquickstart.controller;

        import lombok.extern.slf4j.Slf4j;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import reactor.core.publisher.Flux;
        import reactor.core.publisher.Mono;
        import reactor.core.scheduler.Schedulers;

/**
 * @author finefine at: 2019-06-01 15:02
 */
@RestController
@RequestMapping("/api/")
@Slf4j
public class HelloController {

    @GetMapping("mono")
    public Mono<Object> mono() {
        return Mono.create(monoSink -> {
            log.info("创建 Mono");
            monoSink.success("hello webflux");
        })
                .doOnSubscribe(subscription -> {
                    log.info("{}", subscription);
                }).doOnNext(o -> {
                    log.info("{}", o);
                });
    }

    @GetMapping("flux")
    public Flux<String> flux() {
        return Flux.just("hello","webflux","spring","boot");
    }
}
