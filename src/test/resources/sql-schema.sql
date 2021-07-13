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