package com.baki.order.adapter.out.api;

import com.baki.order.application.port.out.LoadProductPort;
import com.baki.order.common.ProductDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductApiAdapter implements LoadProductPort {
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    public ProductApiAdapter() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public ProductDto load(Long productId) {
        var url = "http://localhost:8082/product/";
        var request = new HttpEntity<>(null, headers);

        return restTemplate.exchange(url+productId, HttpMethod.GET, request, ProductDto.class).getBody();
    }
}
