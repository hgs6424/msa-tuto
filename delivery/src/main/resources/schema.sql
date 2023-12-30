CREATE TABLE deliveries
(
    id          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id    BIGINT,
    type        VARCHAR(15),
    product_id  BIGINT,
    receiver_id BIGINT,
    sender_id   BIGINT,
    status      VARCHAR(15)
);

CREATE TABLE delivery_status_histories
(
    id          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    delivery_id BIGINT,
    status      VARCHAR(15),
    at          DATETIME,
    FOREIGN KEY (delivery_id) REFERENCES `deliveries` (id)
);