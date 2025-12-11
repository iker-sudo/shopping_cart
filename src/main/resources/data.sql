use shopping_cart;

-- Test data for product table
INSERT INTO product (name, description, price, active) VALUES
('Laptop Pro 15"', 'High-performance laptop with 16GB RAM', 1299.99, TRUE),
('Wireless Mouse', 'Ergonomic wireless mouse with USB receiver', 29.99, TRUE),
('Mechanical Keyboard', 'RGB mechanical keyboard with blue switches', 89.99, TRUE),
('USB-C Cable', '2m USB-C to USB-C cable', 15.99, TRUE),
('Monitor 27"', '4K UHD monitor with HDR support', 449.99, TRUE),
('Webcam HD', '1080p webcam with built-in microphone', 79.99, TRUE),
('Headphones', 'Noise-cancelling wireless headphones', 199.99, TRUE),
('Tablet 10"', 'Android tablet with 64GB storage', 299.99, FALSE);

-- Test data for coupon table
INSERT INTO coupon (code, description, discount_type, discount_value, active, valid_from, valid_to) VALUES
('WELCOME10', '10% discount for new customers', 'PERCENT', 10.00, TRUE, '2024-01-01 00:00:00', '2025-12-31 23:59:59'),
('SAVE50', 'Fixed $50 discount on orders over $200', 'FIXED_AMOUNT', 50.00, TRUE, '2024-01-01 00:00:00', '2025-12-31 23:59:59'),
('SPRING25', '25% spring sale discount', 'PERCENT', 25.00, TRUE, '2024-03-01 00:00:00', '2025-05-31 23:59:59'),
('EXPIRED', 'Expired coupon', 'PERCENT', 15.00, FALSE, '2023-01-01 00:00:00', '2023-12-31 23:59:59');

-- Test data for customer_order table
INSERT INTO customer_order (order_number, status, gross_total, discount_total, final_total, payment_method, payment_status, payment_country, billing_name, billing_tax_id, billing_street, billing_city, billing_postal_code, billing_country, shipping_name, shipping_street, shipping_city, shipping_postal_code, shipping_country) VALUES
('ORD-2024-001', 'COMPLETED', 1329.98, 0.00, 1329.98, 'CREDIT_CARD', 'PAID', 'USA', 'John Doe', 'TAX123456', '123 Main St', 'New York', '10001', 'USA', 'John Doe', '123 Main St', 'New York', '10001', 'USA'),
('ORD-2024-002', 'PROCESSING', 779.96, 50.00, 729.96, 'PAYPAL', 'PAID', 'UK', 'Jane Smith', 'GB987654321', '456 High Street', 'London', 'SW1A 1AA', 'UK', 'Jane Smith', '456 High Street', 'London', 'SW1A 1AA', 'UK'),
('ORD-2024-003', 'PENDING', 119.98, 11.998, 107.98, 'BANK_TRANSFER', 'PENDING', 'Spain', 'Carlos Garcia', 'ESB12345678', 'Calle Mayor 10', 'Madrid', '28013', 'Spain', 'Carlos Garcia', 'Calle Mayor 10', 'Madrid', '28013', 'Spain');

-- Test data for order_item table
INSERT INTO order_item (order_id, product_name, unit_price, quantity, product_id) VALUES
(1, 'Laptop Pro 15"', 1299.99, 1, 1),
(1, 'Wireless Mouse', 29.99, 1, 2),
(2, 'Monitor 27"', 449.99, 1, 5),
(2, 'Mechanical Keyboard', 89.99, 1, 3),
(2, 'USB-C Cable', 15.99, 2, 4),
(2, 'Headphones', 199.99, 1, 7),
(3, 'Mechanical Keyboard', 89.99, 1, 3),
(3, 'Wireless Mouse', 29.99, 1, 2);