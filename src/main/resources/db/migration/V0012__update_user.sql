use `myshop`;
alter table `users` add column `cart_id` int default null;
alter table `users` add constraint `fk_cart` foreign key (`cart_id`) references `carts` (`id`);

alter table `smartphones` add column `cart_id` int not null;
alter table `users` add constraint `fk_cart_sm` foreign key (`cart_id`) references `carts` (`id`);
