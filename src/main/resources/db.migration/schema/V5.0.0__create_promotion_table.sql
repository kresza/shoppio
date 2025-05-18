CREATE TABLE promotion (
    id SERIAL PRIMARY KEY,
    promo_code VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(100),
    expiration_date TIMESTAMP
);