use `myshop`;

create table `product_types`
(
    `id`   INT auto_increment NOT NULL,
    `name` varchar(128)       NOT NULL,
    `icon` varchar(128)       NOT NULL,
    PRIMARY KEY (`id`)
);

create table `products`
(
    `id`              INT auto_increment NOT NULL,
    `name`            varchar(128)       NOT NULL,
    `image`           varchar(128)       NOT NULL,
    `description`     varchar(128)       NOT NULL,
    `qty`             INTEGER            NOT NULL,
    `price`           float             not null,
    `product_type_id` int                not null,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_product_type` FOREIGN KEY (`product_type_id`) REFERENCES `product_types` (`id`)
);