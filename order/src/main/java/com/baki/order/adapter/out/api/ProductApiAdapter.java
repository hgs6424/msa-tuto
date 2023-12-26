package com.baki.order.adapter.out.api;

import com.baki.order.application.port.out.LoadProductQuantityPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ProductApiAdapter implements LoadProductQuantityPort {
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    public ProductApiAdapter() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public Integer getQuantity(Long productId) {
        var url = "http://localhost:8082/product/";
        var request = new HttpEntity<>(null, headers);

        var response = restTemplate.exchange(url+productId, HttpMethod.GET, request, ProductDto.class).getBody();

        return Optional.ofNullable(response).map(ProductDto::quantity).orElse(0);
    }

    record ProductDto(
            Long productId,
            Integer quantity
    ) {

    }
}
