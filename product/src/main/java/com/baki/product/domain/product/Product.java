package com.baki.product.domain.product;

class Product {
    private final Id id;
    private Name name;
    private Quantity quantity;
    private Status status;

    public Product(Long id, String name, Integer quantity, String status) {
        this.id = new Id(id);
        this.name = new Name(name);
        this.quantity = new Quantity(quantity);
        this.status = Status.valueOf(status);
    }

    Product(Long id, String name, Integer quantity, Status status) {
        this.id = new Id(id);
        this.name = new Name(name);
        this.quantity = new Quantity(quantity);
        this.status = status;
    }

    public Id getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Status getStatus() {
        return status;
    }

    public static Product create(String name, Integer quantity) {
        return new Product(null, name, quantity, Status.ACTIVE);
    }

    public void changeName(String newName) {
        name = new Name(newName);
    }

    public void decrease(Integer count) {
        this.quantity = new Quantity(this.quantity.value() - count);

        if(this.quantity.value() == 0) {
            sellOut();
        }
    }

    public void increase(Integer count) {
        this.quantity = new Quantity(this.quantity.value() + count);
    }

    public void stopSelling() {
        this.status = Status.INACTIVE;
    }

    public void sellOut() {
        this.status = Status.OUT_OF_STOCK;
    }

}
