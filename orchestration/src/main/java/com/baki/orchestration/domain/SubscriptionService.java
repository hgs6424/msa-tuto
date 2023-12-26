package com.baki.orchestration.domain;

import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    public SubscriptionDto create(SubscriptionDto dto) {
        var subscription = Subscription.create(dto.id(), dto.subscriber(), dto.eventPublisher());
        return new SubscriptionDto(subscription.id().value(), subscription.subscriber().name(), subscription.eventPublisher().name());
    }
}
