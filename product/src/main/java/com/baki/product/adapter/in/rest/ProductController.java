package com.baki.product.adapter.in.rest;

import com.baki.product.application.port.in.GetProductUseCase;
import com.baki.product.domain.product.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("product")
public class ProductController {
    private final GetProductUseCase getProductUseCase;

    public ProductController(GetProductUseCase getProductUseCase) {
        this.getProductUseCase = getProductUseCase;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> get(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(getProductUseCase.get(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
