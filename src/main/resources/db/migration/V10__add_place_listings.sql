create table place_listings(
    id bigint auto_increment primary key,
    name varchar(255) not null,
    description text null,
    place_type varchar(50) not null,
    address varchar(255) not null,
    city varchar(100) not null,
    phone varchar(30) null,
    website varchar(255) null,
    user_id bigint not null,
    created_at timestamp default current_timestamp not null,
    constraint place_listings_users_id_fk
        foreign key (user_id) references users (id)
);