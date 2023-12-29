package com.baki.order.common;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(Long productId) {
        super("product " + productId + " is out of stock");
    }
}
