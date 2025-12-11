drop DATABASE shopping_cart;

create database shopping_cart;

USE shopping_cart;


CREATE TABLE customer_order (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_number VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    gross_total DECIMAL(10, 2) NOT NULL CHECK (gross_total >= 0),
    discount_total DECIMAL(10, 2) DEFAULT 0.00,
    final_total DECIMAL(10, 2) NOT NULL CHECK (final_total >= 0),
    payment_method VARCHAR(50),
    payment_status VARCHAR(50),
    payment_country VARCHAR(100),
    billing_name VARCHAR(255),
    billing_tax_id VARCHAR(50),
    billing_street VARCHAR(255),
    billing_city VARCHAR(100),
    billing_postal_code VARCHAR(20),
    billing_country VARCHAR(100),
    shipping_name VARCHAR(255),
    shipping_street VARCHAR(255),
    shipping_city VARCHAR(100),
    shipping_postal_code VARCHAR(20),
    shipping_country VARCHAR(100)
);

CREATE TABLE coupon (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    code            VARCHAR(50) NOT NULL UNIQUE,
    description     TEXT,
    discount_type   ENUM('PERCENT', 'FIXED_AMOUNT') NOT NULL,
    discount_value  DECIMAL(10,2) NOT NULL,
    active          BOOLEAN NOT NULL DEFAULT TRUE,
    valid_from      DATETIME NULL,
    valid_to        DATETIME NULL
);
CREATE TABLE product (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    description   TEXT,
    price         DECIMAL(10,2) NOT NULL CHECK (price > 0),
    active        BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE order_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL CHECK (unit_price >= 0),
    quantity INT NOT NULL CHECK (quantity > 0),
    line_total DECIMAL(10, 2) GENERATED ALWAYS AS (unit_price * quantity) STORED,
    product_id INT,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES customer_order(id) ON DELETE CASCADE,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id) 
);