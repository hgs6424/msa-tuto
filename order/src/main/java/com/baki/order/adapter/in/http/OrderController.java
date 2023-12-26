package com.baki.order.adapter.in.http;

import com.baki.order.application.port.in.CancelOrderUseCase;
import com.baki.order.application.port.in.PlaceOrderUseCase;
import jakarta.transaction.NotSupportedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
    private final PlaceOrderUseCase placeOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;

    public OrderController(PlaceOrderUseCase placeOrderUseCase, CancelOrderUseCase cancelOrderUseCase) {
        this.placeOrderUseCase = placeOrderUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
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
}
