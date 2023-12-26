package com.baki.orchestration.domain;

public record SubscriptionDto(
        String id,
        String subscriber,
        String eventPublisher
) {
}
