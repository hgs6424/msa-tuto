CREATE TABLE product.products
(
    id       INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(10),
    quantity INT,
    status   VARCHAR(15)
);