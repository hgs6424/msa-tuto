package com.baki.orchestration.application.port.in;

import com.baki.orchestration.domain.EventDto;
import reactor.core.publisher.Mono;

public interface RegisterEventUseCase {
    Mono<Void> register(EventDto eventDto);
}
