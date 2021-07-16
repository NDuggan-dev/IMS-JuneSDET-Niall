drop schema `imstest`;

CREATE SCHEMA IF NOT EXISTS `imstest`;

USE `imstest` ;
CREATE TABLE IF NOT EXISTS `customers` (
    `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
    `forename` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`customer_id`)
);
CREATE TABLE IF NOT EXISTS `items` (
    `item_id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) DEFAULT NULL,
    `value` INT(11) DEFAULT NULL,
    `quantity` INT(11) DEFAULT NULL,
    PRIMARY KEY (`item_id`)
);
CREATE TABLE IF NOT EXISTS `orders` (
    `order_id` INT(11) NOT NULL AUTO_INCREMENT,
    `order_date` DATETIME NOT NULL DEFAULT now(),
    `customer_id` INT(11) NOT NULL,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`customer_id`) REFERENCES customers(`customer_id`)
);
CREATE TABLE IF NOT EXISTS `orders_items` (
    `orders_items_id` INT(11) NOT NULL AUTO_INCREMENT,
    `order_id` INT(11) NOT NULL,
    `item_id` INT(11) NOT NULL,
    PRIMARY KEY (`orders_items_id`),
    FOREIGN KEY (`order_id`) REFERENCES orders(`order_id`),
    FOREIGN KEY (`item_id`) REFERENCES items(`item_id`) 
);