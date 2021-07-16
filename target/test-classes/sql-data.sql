INSERT INTO `customers` (`forename`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`name`, `value`, `quantity`) VALUES ('Deluminator', 300, 20);
INSERT INTO `orders` (`customer_id`, `order_date`) VALUES (1, now());
INSERT INTO `orders_items` (`order_id`, `item_id`) VALUES (1, 1);
