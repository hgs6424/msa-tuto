package com.baki.order.adapter.out.persistence;

import com.baki.order.application.port.out.LoadOrderPort;
import com.baki.order.application.port.out.SaveOrderPort;
import com.baki.order.domain.OrderDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderAdapter implements LoadOrderPort, SaveOrderPort {
    private final OrderRepository orderRepository;

    public OrderAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<OrderDto> load(Long orderId) {
        return orderRepository.findById(orderId).map(entity ->
                new OrderDto(entity.getId(), entity.getProductId(), entity.getCount(), entity.getUserId(), entity.getStatus().name())
        );
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        var entity = new OrderJpaEntity(orderDto.id(), orderDto.productId(), orderDto.count(), orderDto.userId(), Status.valueOf(orderDto.status()));
        entity = orderRepository.save(entity);
        return new OrderDto(entity.getId(), entity.getProductId(), entity.getCount(), entity.getUserId(), entity.getStatus().name());
    }
}
