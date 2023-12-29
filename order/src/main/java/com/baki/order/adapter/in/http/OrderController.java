package com.baki.order.adapter.in.http;

import com.baki.order.application.port.in.CancelOrderUseCase;
import com.baki.order.application.port.in.GetOrderUseCase;
import com.baki.order.application.port.in.PlaceOrderUseCase;
import com.baki.order.common.OutOfStockException;
import com.baki.order.domain.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("order")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final PlaceOrderUseCase placeOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    public OrderController(PlaceOrderUseCase placeOrderUseCase, CancelOrderUseCase cancelOrderUseCase, GetOrderUseCase getOrderUseCase) {
        this.placeOrderUseCase = placeOrderUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> placeOrder(@RequestBody final PlaceOrderRequest request) {
        try {
            placeOrderUseCase.place(request.productId(), request.count(), request.userId());
            return ResponseEntity.ok().build();
        } catch (OutOfStockException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("{id}/status/cancel")
    public void cancelOrder(@PathVariable("id") final Long orderId) {
        cancelOrderUseCase.cancel(orderId);
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
