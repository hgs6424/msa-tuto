package com.baki.order.adapter.in.http;

public record ChangeStatusRequest(
        Long orderId,
        String status
) {
}
