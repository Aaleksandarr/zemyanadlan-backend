create table community_posts (
    id bigint auto_increment primary key,
    title varchar(255) not null,
    content text not null,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    user_id bigint not null,
    constraint community_posts_users_id_fk
        foreign key (user_id) references users (id)
);