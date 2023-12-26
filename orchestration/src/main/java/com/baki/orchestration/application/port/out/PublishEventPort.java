package com.baki.orchestration.application.port.out;

import com.baki.orchestration.domain.EventDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PublishEventPort {
    Mono<Void> publish(Mono<EventDto> dto, Flux<String> subscribers);
}
