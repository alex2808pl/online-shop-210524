-- liquibase formatted sql

-- changeset alex2:insert_test_products
INSERT INTO Products (CategoryID, DiscountPrice, Price, CreatedAt, UpdatedAt, Description, ImageURL, Name)
VALUES
    (1, 15.99, 19.99, NOW(), NOW(), 'Sturdy garden trowel with wooden handle', 'https://example.com/images/garden_trowel.jpg', 'Garden Trowel'),
    (1, 0.0, 39.99, NOW(), NOW(), 'Heavy-duty pruning shears for trimming bushes and small branches', 'https://example.com/images/pruning_shears.jpg', 'Pruning Shears'),
    (2, 299.99, 349.99, NOW(), NOW(), 'Gas-powered lawn mower with 21-inch cutting deck', 'https://example.com/images/lawn_mower.jpg', 'Gas Lawn Mower'),
    (3, 199.99, 849.99, NOW(), NOW(), 'Gas-powered lawn mower with 21-inch cutting deck', 'https://example.com/images/lawn_mower.jpg', 'New Gas Lawn Mower')