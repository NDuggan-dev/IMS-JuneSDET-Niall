drop schema `imstest`;

CREATE SCHEMA IF NOT EXISTS `imstest`;

USE `imstest` ;
CREATE TABLE IF NOT EXISTS `customerss` (
    `customer_ids` INT(11) NOT NULL AUTO_INCREMENT,
    `forenames` VARCHAR(40) DEFAULT NULL,
    `surnames` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`customer_ids`)
);
CREATE TABLE IF NOT EXISTS `itemss` (
    `item_ids` INT(11) NOT NULL AUTO_INCREMENT,
    `names` VARCHAR(40) DEFAULT NULL,
    `values` INT(11) DEFAULT NULL,
    `quantitys` INT(11) DEFAULT NULL,
    PRIMARY KEY (`item_ids`)
);
CREATE TABLE IF NOT EXISTS `orderss` (
    `order_ids` INT(11) NOT NULL AUTO_INCREMENT,
    `order_dates` DATETIME NOT NULL DEFAULT now(),
    `customer_ids` INT(11) NOT NULL,
    PRIMARY KEY (`order_ids`),
    FOREIGN KEY (`customer_ids`) REFERENCES customers(`customer_ids`)
);
CREATE TABLE IF NOT EXISTS `orders_itemss` (
    `orders_items_ids` INT(11) NOT NULL AUTO_INCREMENT,
    `order_ids` INT(11) NOT NULL,
    `item_ids` INT(11) NOT NULL,
    PRIMARY KEY (`orders_items_ids`),
    FOREIGN KEY (`order_ids`) REFERENCES orders(`order_ids`),
    FOREIGN KEY (`item_ids`) REFERENCES items(`item_ids`) 
);