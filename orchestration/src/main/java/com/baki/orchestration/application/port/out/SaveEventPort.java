package com.baki.orchestration.application.port.out;

import com.baki.orchestration.domain.EventDto;
import reactor.core.publisher.Mono;

public interface SaveEventPort {
    Mono<EventDto> save(EventDto eventdto);
}
