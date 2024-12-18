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

