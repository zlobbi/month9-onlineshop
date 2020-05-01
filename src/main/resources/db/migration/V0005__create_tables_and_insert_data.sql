use `myshop`;

create table `brands`
(
    `id`   INT auto_increment NOT NULL,
    `name` varchar(128)       NOT NULL,
    `icon` varchar(128)       NOT NULL,
    PRIMARY KEY (`id`)
);

create table `smartphones`
(
    `id`              INT auto_increment NOT NULL,
    `name`            varchar(128)       NOT NULL,
    `image`           varchar(128)       NOT NULL,
    `description`     varchar(128)       NOT NULL,
    `qty`             INTEGER            NOT NULL,
    `price`           float              not null,
    `brand_id` int                not null,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_brand` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`)
);

insert into `brands` (`name`, `icon`)
values ('Samsung', 'samsung.png'),
       ('Apple', 'apple.png'),
       ('Xiaomi', 'xiaomi.png');

insert into `smartphones` (name, image, description, qty, price, brand_id)
values ('Galaxy A-50', 'a50.png', '', 0, 200.0, 1),
       ('Iphone XR', 'iphoneXR.png', '', 0, 400.0, 2),
       ('Mi 9 SE', 'mi9se.png', '', 0,  300.0, 3);

