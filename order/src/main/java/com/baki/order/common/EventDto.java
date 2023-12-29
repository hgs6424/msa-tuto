package com.baki.order.common;

import java.util.Map;

public record EventDto(
        String eventPublisher,
        String eventType,
        Map<String, Object> payload) {

    public static final String EVENT_PUBLISHER = "ORDER";
    private static final String ORDER_ID_STRING = "orderId";

    public static EventDto placeOrderEvent(Long orderId) {
        return new EventDto(EVENT_PUBLISHER, "PlaceOrder", Map.of(ORDER_ID_STRING, orderId));
    }

    public static EventDto cancelOrderEvent(Long orderId) {
        return new EventDto(EVENT_PUBLISHER, "CancelOrder", Map.of(ORDER_ID_STRING, orderId));
    }

    public static EventDto completeOrderEvent(Long orderId) {
        return new EventDto(EVENT_PUBLISHER, "CompleteOrder", Map.of(ORDER_ID_STRING, orderId));
    }
}
