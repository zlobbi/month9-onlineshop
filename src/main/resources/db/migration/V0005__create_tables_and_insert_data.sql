use `myshop`;

create table `Brand`
(
    `id`   INT auto_increment NOT NULL,
    `name` varchar(128)       NOT NULL,
    `icon` varchar(128)       NOT NULL,
    PRIMARY KEY (`id`)
);

create table `Smartphone`
(
    `id`          INT auto_increment not null,
    `name`        varchar(128)       NOT NULL,
    `image`       varchar(128)       NOT NULL,
    `description` varchar(128)       NOT NULL,
    `qty`         int                NOT NULL,
    `price`       double             not null,
    `brand_id`    int                not null,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_brand` FOREIGN KEY (`brand_id`) REFERENCES `Brand` (`id`)
);

insert into `Brand` (`name`, `icon`)
values ('Samsung', 'samsung.png'),
       ('Apple', 'apple.png'),
       ('Xiaomi', 'xiaomi.png'),
       ('No-brand', 'no-brand.png');

insert into `Smartphone` (name, image, description, qty, price, brand_id)
values ('Galaxy A-50', 'a50.png', '', 0, 200.0, 1),
       ('Iphone XR', 'iphoneXR.png', '', 0, 400.0, 2),
       ('Mi 9 SE', 'mi9se.png', '', 0, 300.0, 3),
       ('Galaxy A-70', 'a50.png', '', 0, 300.50, 1),
       ('Iphone XS', 'iphoneXR.png', '', 0, 420.60, 2),
       ('Mi 8 SE', 'mi9se.png', '', 0, 290.50, 3);

create table `User`
(
    `id`       INT auto_increment NOT NULL,
    `login`    varchar(128)       NOT NULL,
    `email`    varchar(128)       NOT NULL,
    `password` varchar(128)       NOT NULL,
    `role`     varchar(128)       not null,
    `enabled`  boolean,
    PRIMARY KEY (`id`)
);

create table `Cart`
(
    `id`            int auto_increment,
    `session`       varchar(128)       not null,
    `user_id`       int default null
,
    primary key (`id`),
    constraint `fk_user` foreign key (`user_id`) references `User` (`id`)
);

create table `Cart_Smartphone`
(
    `id` int auto_increment not null ,
    `session` varchar(255) not null ,
    `cart_id` int ,
    `smartphone_id` int not null ,
    `qty` int not null ,
    `sum` double not null ,
    primary key (`id`),
    constraint `fk_cart` foreign key (`cart_id`) references `Cart` (`id`) on delete cascade ,
    constraint `fk_smartphone_` foreign key (`smartphone_id`) references `Smartphone` (`id`)
)


