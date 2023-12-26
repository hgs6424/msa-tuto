package com.baki.orchestration.adapter.in.http;

public record RegisterEventRequest(
        String eventPublisher,
        String eventType,
        String payload
) {
}
