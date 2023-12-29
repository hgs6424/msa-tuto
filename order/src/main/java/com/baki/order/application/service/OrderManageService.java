package com.baki.order.application.service;

import com.baki.order.application.port.in.CancelOrderUseCase;
import com.baki.order.application.port.in.GetOrderUseCase;
import com.baki.order.application.port.in.PlaceOrderUseCase;
import com.baki.order.application.port.out.LoadOrderPort;
import com.baki.order.application.port.out.LoadProductPort;
import com.baki.order.application.port.out.PublishEventPort;
import com.baki.order.application.port.out.SaveOrderPort;
import com.baki.order.common.EventDto;
import com.baki.order.common.OutOfStockException;
import com.baki.order.domain.OrderDto;
import com.baki.order.domain.OrderService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrderManageService implements PlaceOrderUseCase, CancelOrderUseCase, GetOrderUseCase {
    private final LoadOrderPort loadOrderPort;
    private final SaveOrderPort saveOrderPort;
    private final LoadProductPort loadProductPort;
    private final PublishEventPort publishEventPort;
    private final OrderService orderService;

    public OrderManageService(LoadOrderPort loadOrderPort, SaveOrderPort saveOrderPort,
                              LoadProductPort loadProductPort, PublishEventPort publishEventPort,
                              OrderService orderService) {
        this.loadOrderPort = loadOrderPort;
        this.saveOrderPort = saveOrderPort;
        this.loadProductPort = loadProductPort;
        this.publishEventPort = publishEventPort;
        this.orderService = orderService;
    }


    @Override
    public void cancel(Long orderId) {
        var orderDto = loadOrderPort.load(orderId).orElseThrow(NoSuchElementException::new);
        orderDto = orderService.cancel(orderDto);
        orderDto = saveOrderPort.save(orderDto);
        var eventDto = EventDto.cancelOrderEvent(orderDto.id());
        publishEventPort.publish(eventDto);
    }

    @Override
    public void place(Long productId, Integer count, Long userId) throws OutOfStockException {
        var productDto = loadProductPort.load(productId);

        if (productDto.quantity() < count) {
            throw new OutOfStockException(productId);
        }

        var orderDto = orderService.create(productId, count, userId);
        orderDto = saveOrderPort.save(orderDto);
        var eventDto = EventDto.placeOrderEvent(orderDto.id());
        publishEventPort.publish(eventDto);
    }

    @Override
    public OrderDto get(Long orderId) {
        return loadOrderPort.load(orderId).orElseThrow(NoSuchElementException::new);
    }
}
