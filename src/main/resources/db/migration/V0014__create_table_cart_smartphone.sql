use `myshop`;

create table `cart_smartphone` (
    `id` int not null,
    `carts_id` int not null ,
    `my_smartphones_id` int not null ,
    constraint `fk_card` foreign key (`carts_id`) references `carts` (`id`),
    constraint `fk_smartphone` foreign key (`my_smartphones_id`) references `smartphones` (`id`)
)

