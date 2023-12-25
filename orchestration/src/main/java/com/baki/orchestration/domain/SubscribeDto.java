package com.baki.orchestration.domain;

public record SubscribeDto(
        String id,
        String subscriber,
        String eventPublisher
) {
}
