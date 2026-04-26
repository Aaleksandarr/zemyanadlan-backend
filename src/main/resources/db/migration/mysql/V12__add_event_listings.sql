create table event_listings (
    id bigint auto_increment primary key,
    name varchar(255) not null,
    description text null,
    event_type varchar(50)  not null,
    city varchar(100) not null,
    address varchar(255) not null,
    created_at timestamp default current_timestamp not null,
    user_id bigint not null,
    starts_at datetime not null,
    constraint event_listings_users_id_fk
        foreign key (user_id) references users (id)
);