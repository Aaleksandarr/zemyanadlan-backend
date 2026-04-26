create table market_listing_categories
(
    listing_id  bigint not null,
    category_id bigint not null,
    constraint market_listing_categories_pk
        primary key (listing_id, category_id),
    constraint market_listing_categories_categories_id_fk
        foreign key (category_id) references categories (id),
    constraint market_listing_categories_market_listings_id_fk
        foreign key (listing_id) references market_listings (id)
);