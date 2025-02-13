-- liquibase formatted sql

-- changeset alex2:Categories
CREATE TABLE Categories (CategoryID INT AUTO_INCREMENT NOT NULL,
Name VARCHAR(255) NULL,
CONSTRAINT PK_CATEGORIES PRIMARY KEY (CategoryID));

-- changeset alex2:Products
CREATE TABLE Products (ProductID INT AUTO_INCREMENT NOT NULL,
Name VARCHAR(255) NULL,
CategoryID INT NULL,
DiscountPrice DOUBLE NULL,
Price DOUBLE NULL,
CreatedAt datetime NULL,
UpdatedAt datetime NULL,
`Description` VARCHAR(255) NULL,
ImageURL VARCHAR(255) NULL,
CONSTRAINT PK_PRODUCTS PRIMARY KEY (ProductID));

-- changeset alex2:IndexProductsFk
CREATE INDEX FK_PRODUCTS_CATEGORYID_IDX ON Products(CategoryID);

-- changeset alex2:REFERENCES_Products_Categories
ALTER TABLE Products ADD CONSTRAINT FK_PRODUCTS_CATEGORYID_REFERENCES FOREIGN KEY (CategoryID) REFERENCES Categories (CategoryID) ON UPDATE RESTRICT ON DELETE RESTRICT;

-- changeset alex2:create_table_users
CREATE TABLE users (userId INT AUTO_INCREMENT NOT NULL,
name VARCHAR(255) NULL, email VARCHAR(255) NULL,
phoneNumber VARCHAR(255) NULL,
passwordHash VARCHAR(255) NULL,
role ENUM('CLIENT', 'ADMIN') NULL,
refreshToken VARCHAR(255) NULL,
CONSTRAINT PK_USERS PRIMARY KEY (userId));

-- changeset alex2:create_table_cart
CREATE TABLE cart (cartId INT AUTO_INCREMENT NOT NULL,
userId INT NULL, CONSTRAINT PK_CART PRIMARY KEY (cartId), UNIQUE (userId));

-- changeset alex2:create_table_favorites
CREATE TABLE favorites (favoriteId INT AUTO_INCREMENT NOT NULL, productId INT NULL,
userId INT NULL, CONSTRAINT PK_FAVORITES PRIMARY KEY (favoriteId));




