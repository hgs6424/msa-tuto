package com.baki.orchestration.application.port.in;

import com.baki.orchestration.domain.SubscriptionDto;
import reactor.core.publisher.Mono;

public interface RegisterSubscriptionUseCase {
    Mono<Void> register(SubscriptionDto dto);
}
