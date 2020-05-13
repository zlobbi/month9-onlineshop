use `myshop`;
alter table `users` add column `cart_id` int default null;
alter table `users` add constraint `fk_cart` foreign key (`cart_id`) references `carts` (`id`);

