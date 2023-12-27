package com.baki.order.application.service;

import com.baki.order.application.EventDto;
import com.baki.order.application.port.in.CancelOrderUseCase;
import com.baki.order.application.port.in.GetOrderUseCase;
import com.baki.order.application.port.in.PlaceOrderUseCase;
import com.baki.order.application.port.out.LoadOrderPort;
import com.baki.order.application.port.out.LoadProductQuantityPort;
import com.baki.order.application.port.out.PublishEventPort;
import com.baki.order.application.port.out.SaveOrderPort;
import com.baki.order.domain.OrderDto;
import com.baki.order.domain.OrderService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class OrderManageService implements PlaceOrderUseCase, CancelOrderUseCase, GetOrderUseCase {
    private final LoadOrderPort loadOrderPort;
    private final SaveOrderPort saveOrderPort;
    private final LoadProductQuantityPort loadProductQuantityPort;
    private final PublishEventPort publishEventPort;
    private final OrderService orderService;

    public OrderManageService(LoadOrderPort loadOrderPort, SaveOrderPort saveOrderPort,
                              LoadProductQuantityPort loadProductQuantityPort, PublishEventPort publishEventPort,
                              OrderService orderService) {
        this.loadOrderPort = loadOrderPort;
        this.saveOrderPort = saveOrderPort;
        this.loadProductQuantityPort = loadProductQuantityPort;
        this.publishEventPort = publishEventPort;
        this.orderService = orderService;
    }


    @Override
    public void cancel(Long orderId) {
        var orderDto = loadOrderPort.load(orderId).orElseThrow(NoSuchElementException::new);
        orderDto = orderService.cancel(orderDto);
        orderDto = saveOrderPort.save(orderDto);
        var eventDto = new EventDto("ORDER", "CancelOrder", Map.of("orderId", orderDto.id()));
        publishEventPort.publish(eventDto);
    }

    @Override
    public void place(Long productId, Integer count, Long userId) throws RuntimeException{
        var productQuantity = loadProductQuantityPort.getQuantity(productId);

        if (productQuantity < count) {
            throw new RuntimeException("product " + productId + " is out of stock");
        }

        var orderDto = orderService.create(productId, count, userId);
        orderDto = saveOrderPort.save(orderDto);
        var eventDto = new EventDto("ORDER", "PlaceOrder", Map.of("orderId", orderDto.id()));
        publishEventPort.publish(eventDto);
    }

    @Override
    public OrderDto get(Long orderId) {
        return loadOrderPort.load(orderId).orElseThrow(NoSuchElementException::new);
    }
}
