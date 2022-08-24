DROP TABLE IF EXISTS `ssg`.`users`;
DROP TABLE IF EXISTS `ssg`.`products`;
DROP TABLE IF EXISTS `ssg`.`orders`;

CREATE TABLE `ssg`.`users` (
                              `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                              `name` VARCHAR(10) NOT NULL,
                              `club_type` VARCHAR(20) NOT NULL,
                              `created_at` DATETIME NOT NULL,
                              `updated_at` DATETIME NOT NULL,
                              PRIMARY KEY (`user_id`));

CREATE TABLE `ssg`.`products` (
                                 `product_id` BIGINT NOT NULL AUTO_INCREMENT,
                                 `name` VARCHAR(45) NOT NULL,
                                 `price` INT NOT NULL,
                                 `delivery_price` INT NOT NULL,
                                 `stock_count` INT NOT NULL,
                                 `created_at` DATETIME NOT NULL,
                                 `updated_at` DATETIME NOT NULL,
                                 PRIMARY KEY (`product_id`));

CREATE TABLE `ssg`.`orders` (
                               `order_id` BIGINT NOT NULL AUTO_INCREMENT,
                               `user_id` BIGINT NOT NULL,
                               `product_id` BIGINT NOT NULL,
                               `product_count` INT NOT NULL,
                               `order_price` INT NOT NULL,
                               `final_price` INT NOT NULL,
                               `delivery_price` INT NOT NULL,
                               `created_at` DATETIME NOT NULL,
                               `updated_at` DATETIME NOT NULL,
                               PRIMARY KEY (`order_id`));
