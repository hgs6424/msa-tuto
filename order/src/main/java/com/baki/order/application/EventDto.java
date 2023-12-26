package com.baki.order.application;

import java.util.Map;

public record EventDto(
        String eventPublisher,
        String eventType,
        Map<String, Object> payload) {
}
