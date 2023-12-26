package com.baki.orchestration.application.port.out;

import com.baki.orchestration.domain.SubscriptionDto;
import reactor.core.publisher.Flux;

public interface LoadSubscriptionByPublisherPort {
    Flux<SubscriptionDto> load(String domainServiceType);
}
