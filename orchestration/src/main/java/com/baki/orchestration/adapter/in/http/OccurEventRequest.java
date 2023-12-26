package com.baki.orchestration.adapter.in.http;

public record OccurEventRequest(
        String eventPublisher,
        String eventType,
        String payload
) {
}
