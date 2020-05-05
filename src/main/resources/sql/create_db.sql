DROP DATABASE IF EXISTS ishop;
CREATE SCHEMA ishop;

CREATE TABLE ishop.role
(
    id     INT         NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO `ishop`.`role` (id, `name`)
VALUES ('1', 'customer');
INSERT INTO `ishop`.`role` (`id`, `name`)
VALUES ('2', 'admin');

CREATE TABLE `ishop`.`user`
(
    id                 INT                NOT NULL AUTO_INCREMENT,
    login              VARCHAR(45) UNIQUE NOT NULL,
    password           VARCHAR(45)        NOT NULL,
    email              VARCHAR(45)        NOT NULL,
    first_name         VARCHAR(45)        NOT NULL,
    last_name          VARCHAR(45)        NOT NULL,
    avatar_link        VARCHAR(255)       NOT NULL,
    newsletter_consent TINYINT            NOT NULL,
    role_id            INT                NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    INDEX `role_name_idx` (role_id ASC) VISIBLE,
    CONSTRAINT `role_name`
        FOREIGN KEY (role_id)
            REFERENCES `ishop`.`role` (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

INSERT INTO `ishop`.`user` ( `login`, `password`, `email`, `first_name`, `last_name`, `avatar_link`, `newsletter_consent`) VALUES ( 'admin', '123123123', 'admin@admin.ccom', 'admin', 'admin', 'basic.png', '0');

CREATE TABLE ishop.category
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    url  VARCHAR(60) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
    UNIQUE INDEX url_UNIQUE (url ASC) VISIBLE
);

CREATE TABLE ishop.producer
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE
);

CREATE TABLE `ishop`.`product`
(
    id          INT           NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255)  NOT NULL,
    description TEXT          NOT NULL,
    image_link  VARCHAR(255)  NOT NULL,
    price       DECIMAL(8, 2) NOT NULL,
    category_id INT           NOT NULL,
    producer_id INT           NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX `id_UNIQUE` (id ASC) VISIBLE,
    INDEX `producer_idx` (producer_id ASC) VISIBLE,
    INDEX `category_idx` (category_id ASC) VISIBLE,
    CONSTRAINT `producer`
        FOREIGN KEY (producer_id)
            REFERENCES `ishop`.`producer` (id)
            ON DELETE RESTRICT
            ON UPDATE CASCADE,
    CONSTRAINT `category`
        FOREIGN KEY (category_id)
            REFERENCES `ishop`.`category` (id)
            ON DELETE RESTRICT
            ON UPDATE CASCADE
);
CREATE TABLE `ishop`.`status`
(
    `id`   INT         NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
);
INSERT INTO `ishop`.`status` (`id`, `name`)
VALUES ('1', 'accepted');
INSERT INTO `ishop`.`status` (`id`, `name`)
VALUES ('2', 'confirmed');
INSERT INTO `ishop`.`status` (`id`, `name`)
VALUES ('3', 'formed');
INSERT INTO `ishop`.`status` (`id`, `name`)
VALUES ('4', 'sent');
INSERT INTO `ishop`.`status` (`id`, `name`)
VALUES ('5', 'completed');
INSERT INTO `ishop`.`status` (`id`, `name`)
VALUES ('6', 'cancelled');

CREATE TABLE `ishop`.`payment`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);


INSERT INTO `ishop`.`payment` (`id`, `name`)
VALUES ('1', 'cash');
INSERT INTO `ishop`.`payment` (`id`, `name`)
VALUES ('2', 'credit');
INSERT INTO `ishop`.`payment` (`id`, `name`)
VALUES ('3', 'transfer');


CREATE TABLE `ishop`.`delivery`
(
    `id`   INT         NOT NULL,
    `name` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `ishop`.`delivery` (`id`, `name`)
VALUES ('1', 'ground(7-15 days)');
INSERT INTO `ishop`.`delivery` (`id`,`name`)
VALUES ('2','air(1-5 days)');

CREATE TABLE `ishop`.`order`
(
    `id`           VARCHAR(45) NOT NULL,
    `address`      TEXT,
    `phone_number` VARCHAR(20),
    `delivery_id`  INT         NOT NULL,
    `payment_id`   INT         NOT NULL,
    `user_id`      INT         NOT NULL,
    `status_id`    INT         NOT NULL DEFAULT 1,
    `description`  TEXT        NULL,
    `created`      DATETIME    NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`),
    INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
    INDEX `status_id_idx` (`status_id` ASC) VISIBLE,
    INDEX `delivery_idx` (`delivery_id` ASC) VISIBLE,
    INDEX `payment_idx` (`payment_id` ASC) VISIBLE,
    CONSTRAINT `user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `ishop`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `status_id`
        FOREIGN KEY (`status_id`)
            REFERENCES `ishop`.`status` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `delivery`
        FOREIGN KEY (`delivery_id`)
            REFERENCES `ishop`.`delivery` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `payment`
        FOREIGN KEY (`payment_id`)
            REFERENCES `ishop`.`payment` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `ishop`.`order_item`
(
    `id`         INT           NOT NULL AUTO_INCREMENT,
    `order_id`   VARCHAR(45)   NOT NULL,
    `product_id` INT           NOT NULL,
    `price`      DECIMAL(8, 2) NOT NULL,
    `count`      INT           NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `order_id_idx` (`order_id` ASC) VISIBLE,
    INDEX `product_id_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `order_id`
        FOREIGN KEY (`order_id`)
            REFERENCES `ishop`.`order` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `product_id`
        FOREIGN KEY (`product_id`)
            REFERENCES `ishop`.`product` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


