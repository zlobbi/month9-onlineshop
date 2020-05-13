use `myshop`;

create table `cart_smartphone` (
    `id` int not null,
    `cart_id` int not null ,
    `smartphone_id` int not null ,
    constraint `fk_card` foreign key (`cart_id`) references `carts` (`id`),
    constraint `fk_smartphone` foreign key (`smartphone_id`) references `smartphones` (`id`)
)

