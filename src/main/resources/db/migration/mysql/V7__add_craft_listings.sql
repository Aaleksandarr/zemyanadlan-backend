create table craft_listings (
    id bigint auto_increment primary key,
    title varchar(255) not null,
    description text null,
    price DECIMAL(10, 2) null,
    material varchar(100) not null,
    created_at timestamp default current_timestamp not null,
    user_id bigint not null,
    is_customizable boolean not null,
    constraint craft_listings_users_id_fk
        foreign key (user_id) references users (id)
);