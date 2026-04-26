create table post_categories (
    post_id bigint not null,
    category_id bigint not null,
    constraint post_categories_pk
        primary key (post_id, category_id),
    constraint post_categories_categories_id_fk
        foreign key (category_id) references categories (id),
    constraint post_categories_community_posts_id_fk
        foreign key (post_id) references community_posts (id)
);