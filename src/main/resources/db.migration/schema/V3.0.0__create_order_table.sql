CREATE TABLE IF NOT EXISTS cart (
    cart_id VARCHAR(8) NOT NULL UNIQUE  PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);