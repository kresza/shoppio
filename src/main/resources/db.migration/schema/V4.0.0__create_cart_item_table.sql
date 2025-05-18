CREATE TABLE cart_item (
    id SERIAL PRIMARY KEY,
    cart_id VARCHAR(8) NOT NULL,
    product_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity > 0),

    CONSTRAINT unique_cart_item_product UNIQUE (cart_id, product_id),
    CONSTRAINT fk_cart_item_to_cart FOREIGN KEY (cart_id)
        REFERENCES cart(cart_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_cart_item_to_product FOREIGN KEY (product_id)
        REFERENCES product(id)
        ON DELETE CASCADE
);