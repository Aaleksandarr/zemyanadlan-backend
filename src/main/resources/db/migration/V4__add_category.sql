create table categories
(
    id             bigint auto_increment
        primary key,
    name           varchar(100) not null,
    slug           varchar(100) not null,
    category_scope varchar(50)  not null
);