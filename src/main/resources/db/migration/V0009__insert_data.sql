use `myshop`;

alter table users add column enabled boolean after password;
alter table users add column role varchar(128) after enabled;

