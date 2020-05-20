use `myshop`;

create table `password_reset_token`
(
    `id`      int auto_increment not null,
    `token`   varchar(255),
    `user_id` int,
    primary key (`id`),
    constraint `fk_user__` foreign key (`user_id`) references `user` (`id`)
)


