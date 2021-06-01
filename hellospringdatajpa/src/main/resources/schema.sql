# create database sales default character set utf8 collate utf8_general_ci;

drop table if exists app_product;

create table app_product (
    id bigint not null auto_increment,
    name varchar(128) not null,
    brand varchar(128) not null,
    madein varchar(128) not null,
    price double not null,
    primary key (id)
) default character set utf8 collate utf8_general_ci;
