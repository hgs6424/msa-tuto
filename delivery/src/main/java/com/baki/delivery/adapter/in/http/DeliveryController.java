package com.baki.delivery.adapter.in.http;

import com.baki.delivery.application.port.in.FinishDeliveryUseCase;
import com.baki.delivery.application.port.in.StartDeliveryUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("delivery")
public class DeliveryController {
    private final StartDeliveryUseCase startDeliveryUseCase;
    private final FinishDeliveryUseCase finishDeliveryUseCase;

    public DeliveryController(StartDeliveryUseCase startDeliveryUseCase, FinishDeliveryUseCase finishDeliveryUseCase) {
        this.startDeliveryUseCase = startDeliveryUseCase;
        this.finishDeliveryUseCase = finishDeliveryUseCase;
    }

    @PutMapping("{id}/status/shipping")
    public void startDelivery(@PathVariable("id") final Long id) {
        startDeliveryUseCase.start(id);
    }

    @PutMapping("{id}/status/shipped")
    public void finishDelivery(@PathVariable("id") final Long id) {
        finishDeliveryUseCase.finish(id);
    }
}
