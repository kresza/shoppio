CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(12,2) NOT NULL,
    available BOOLEAN NOT NULL DEFAULT TRUE,
    category_id INTEGER NOT NULL,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id)
        REFERENCES dict_category(id)
        ON DELETE CASCADE
);