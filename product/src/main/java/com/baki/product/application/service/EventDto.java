package com.baki.product.application.service;

import java.util.Map;

public record EventDto(
        String eventPublisher,
        String eventType,
        Map<String, Object> payload) {
}