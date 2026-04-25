create table market_listings (
    id bigint auto_increment primary key,
    title varchar(255) not null,
    description text null,
    price DECIMAL(10, 2) not null,
    unit varchar(10) null,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    user_id bigint not null,
    constraint market_listings_users_id_fk
        foreign key (user_id) references users (id)
);
