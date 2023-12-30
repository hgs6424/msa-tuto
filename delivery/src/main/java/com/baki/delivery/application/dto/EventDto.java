package com.baki.delivery.application.dto;

import java.util.Map;

public record EventDto(
        String eventPublisher,
        String eventType,
        Map<String, Object> payload
) {
    private static final String EVENT_PUBLISHER = "DELIVERY";
    private static final String DELIVERY_ID_STRING = "deliveryId";

    public static EventDto requestDeliveryEvent(Long deliveryId) {
        return new EventDto(EVENT_PUBLISHER, "RequestDelivery", Map.of(DELIVERY_ID_STRING, deliveryId));
    }

    public static EventDto startDeliveryEvent(Long deliveryId) {
        return new EventDto(EVENT_PUBLISHER, "StartDelivery", Map.of(DELIVERY_ID_STRING, deliveryId));
    }

    public static EventDto finishDeliveryEvent(Long deliveryId) {
        return new EventDto(EVENT_PUBLISHER, "FinishDelivery", Map.of(DELIVERY_ID_STRING, deliveryId));
    }
}
