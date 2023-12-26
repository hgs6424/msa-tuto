package com.baki.orchestration.adapter.in.http;

record RegisterSubscriptionRequest(
        String subscriber,
        String eventPublisher
) {
}
