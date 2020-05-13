use `myshop`;

create table `carts` (
    `id` int auto_increment not null,
    `session` varchar(128) not null,
    `user_id` int default null,
    `smartphone_id` int not null,
    primary key (`id`),
    constraint `fk_user` foreign key (`user_id`) references `users` (`id`),
    constraint `smartphone` foreign key (`smartphone_id`) references `smartphones` (`id`)
)

