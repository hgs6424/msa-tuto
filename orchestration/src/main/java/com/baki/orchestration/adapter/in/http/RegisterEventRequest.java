package com.baki.orchestration.adapter.in.http;

record RegisterEventRequest(
        String eventPublisher,
        String eventType,
        String payload
) {
}
