CREATE TABLE orders
(
    id         BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,
    count      INT,
    user_id    BIGINT,
    status     VARCHAR(15)
);