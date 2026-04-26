create table place_listing_categories (
    listing_id  bigint not null,
    category_id bigint not null,
    constraint place_listing_categories_pk
        primary key (listing_id, category_id),
    constraint place_listing_categories_categories_id_fk
        foreign key (category_id) references categories (id),
    constraint place_listing_categories_place_listings_id_fk
        foreign key (listing_id) references place_listings (id)
);