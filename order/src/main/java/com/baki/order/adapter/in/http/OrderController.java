package com.baki.order.adapter.in.http;

import com.baki.order.application.port.in.CancelOrderUseCase;
import com.baki.order.application.port.in.GetOrderUseCase;
import com.baki.order.application.port.in.PlaceOrderUseCase;
import com.baki.order.domain.OrderDto;
import jakarta.transaction.NotSupportedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("order")
public class OrderController {
    private final PlaceOrderUseCase placeOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderController(PlaceOrderUseCase placeOrderUseCase, CancelOrderUseCase cancelOrderUseCase, GetOrderUseCase getOrderUseCase) {
        this.placeOrderUseCase = placeOrderUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
    }

    @PostMapping
    public void placeOrder(@RequestBody final PlaceOrderRequest request) {
        placeOrderUseCase.place(request.productId(), request.count(), request.userId());
    }

    @PutMapping("{id}/status")
    public void changeStatus(@PathVariable("id") final Long orderId,
                             @RequestBody final ChangeStatusRequest request) throws NotSupportedException {
        if(request.status().equals("CANCELED")) {
            cancelOrderUseCase.cancel(orderId);
        } else {
            throw new NotSupportedException();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> get(@PathVariable("id") final Long orderId) {
        log.info("[order get api called] orderId = {}", orderId);
        try {
            return ResponseEntity.ok(getOrderUseCase.get(orderId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
