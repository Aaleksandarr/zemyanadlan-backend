create table users(
    id bigint auto_increment primary key,
    username varchar(50) not null,
    email varchar(255) not null,
    created_at timestamp default current_timestamp not null
);