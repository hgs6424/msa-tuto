package com.baki.orchestration.adapter.in.http;

public record RegisterSubscriptionRequest(
        String subscriber,
        String eventPublisher
) {
}
