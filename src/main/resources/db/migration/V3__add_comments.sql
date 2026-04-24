create table comments(
    id bigint auto_increment primary key,
    content text not null,
    created_at timestamp default current_timestamp not null,
    user_id bigint not null,
    community_post_id bigint not null,
    constraint comments_community_posts_id_fk
        foreign key (community_post_id) references community_posts (id),
    constraint comments_users_id_fk
        foreign key (user_id) references users (id)
);