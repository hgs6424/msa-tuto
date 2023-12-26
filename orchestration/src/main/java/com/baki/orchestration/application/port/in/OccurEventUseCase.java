package com.baki.orchestration.application.port.in;

import com.baki.orchestration.domain.EventDto;
import reactor.core.publisher.Mono;

public interface OccurEventUseCase {
    Mono<Void> occur(EventDto eventDto);
}
