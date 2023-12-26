package com.baki.orchestration.domain;

import java.util.Map;

public record EventDto(
        String id,
        String eventPublisher,
        String eventType,
        Map<String, Object> payload
) {
}
