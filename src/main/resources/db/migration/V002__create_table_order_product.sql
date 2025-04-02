CREATE TABLE IF NOT EXISTS order_product (
    id                  UUID                NOT NULL,
    order_id            UUID                NOT NULL,
    name                VARCHAR(150)        NOT NULL,
    value               NUMERIC(10, 2)      NOT NULL,

    CONSTRAINT pkey_order_product       PRIMARY KEY (id),
    CONSTRAINT fkey_order_product_order FOREIGN KEY (order_id) REFERENCES "order"(id)
);

CREATE INDEX IF NOT EXISTS idx_order_product_order_id ON order_product (order_id);