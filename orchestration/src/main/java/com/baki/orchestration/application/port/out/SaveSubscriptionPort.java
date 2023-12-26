package com.baki.orchestration.application.port.out;

import com.baki.orchestration.domain.SubscriptionDto;
import reactor.core.publisher.Mono;

public interface SaveSubscriptionPort {
    Mono<SubscriptionDto> save(SubscriptionDto subscriptionDto);
}
