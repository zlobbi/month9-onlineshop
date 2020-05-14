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
    `id`          INT auto_increment not null,
    `name`        varchar(128)       NOT NULL,
    `image`       varchar(128)       NOT NULL,
    `description` varchar(128)       NOT NULL,
    `qty`         int                NOT NULL,
    `price`       double             not null,
    `brand_id`    int                not null,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_brand` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`)
);

insert into `brands` (`name`, `icon`)
values ('Samsung', 'samsung.png'),
       ('Apple', 'apple.png'),
       ('Xiaomi', 'xiaomi.png'),
       ('No-brand', 'no-brand.png');

insert into `smartphones` (name, image, description, qty, price, brand_id)
values ('Galaxy A-50', 'a50.png', '', 0, 200.0, 1),
       ('Iphone XR', 'iphoneXR.png', '', 0, 400.0, 2),
       ('Mi 9 SE', 'mi9se.png', '', 0, 300.0, 3),
       ('Galaxy A-70', 'a50.png', '', 0, 300.50, 1),
       ('Iphone XS', 'iphoneXR.png', '', 0, 420.60, 2),
       ('Mi 8 SE', 'mi9se.png', '', 0, 290.50, 3);

create table `users`
(
    `id`       INT auto_increment NOT NULL,
    `login`    varchar(128)       NOT NULL,
    `email`    varchar(128)       NOT NULL,
    `password` varchar(128)       NOT NULL,
    `role`     varchar(128)       not null,
    `enabled`  boolean,
    PRIMARY KEY (`id`)
);

create table `carts`
(
    `id`            int auto_increment not null,
    `session`       varchar(128)       not null,
    `user_id`       int default null,
    `smartphone_id` int                default null
,
    primary key (`id`),
    constraint `fk_user` foreign key (`user_id`) references `users` (`id`),
    constraint `smartphone` foreign key (`smartphone_id`) references `smartphones` (`id`)
)



