package com.baki.orchestration.domain;

public record EventDto(
        String id,
        String eventPublisher,
        String eventType,
        String payload
) {
}
