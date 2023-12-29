package com.baki.order.domain;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public OrderDto create(Long productId, Integer count, Long userId) {
        var order = Order.create(productId, count, userId);
        return new OrderDto(order.getId().value(),
                order.getProdcutId().value(),
                order.getCount().value(),
                order.getUserId().value(),
                order.getStatus().name());
    }

    public OrderDto cancel(OrderDto orderDto) {
        var order = Order.fromDto(orderDto);
        order.cancel();
        return new OrderDto(order.getId().value(),
                order.getProdcutId().value(),
                order.getCount().value(),
                order.getUserId().value(),
                order.getStatus().name());
    }

    public OrderDto reject(OrderDto orderDto) {
        var order = Order.fromDto(orderDto);
        order.reject();
        return new OrderDto(order.getId().value(),
                order.getProdcutId().value(),
                order.getCount().value(),
                order.getUserId().value(),
                order.getStatus().name());
    }
}
