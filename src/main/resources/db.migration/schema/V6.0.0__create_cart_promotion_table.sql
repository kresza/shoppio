CREATE TABLE cart_promotion (
    id SERIAL PRIMARY KEY,
    cart_id VARCHAR(8) NOT NULL,
    promotion_id INTEGER NOT NULL,

    CONSTRAINT fk_cart_promotion_to_cart FOREIGN KEY (cart_id) REFERENCES cart(cart_id) ON DELETE CASCADE,
    CONSTRAINT fk_cart_promotion_to_promotion FOREIGN KEY (promotion_id) REFERENCES promotion(id),
    CONSTRAINT unique_cart_promotion UNIQUE(cart_id, promotion_id)
);