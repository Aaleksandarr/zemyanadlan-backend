create table craft_listing_categories
(
    listing_id  bigint not null,
    category_id bigint not null,
    constraint craft_listing_categories_pk
        primary key (listing_id, category_id),
    constraint craft_listing_categories_categories_id_fk
        foreign key (category_id) references categories (id),
    constraint craft_listing_categories_craft_listings_id_fk
        foreign key (listing_id) references craft_listings (id)
);