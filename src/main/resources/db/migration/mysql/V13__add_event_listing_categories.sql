create table event_listing_categories
(
    listing_id  bigint not null,
    category_id bigint not null,
    constraint event_listing_categories_pk
        primary key (listing_id, category_id),
    constraint event_listing_categories_categories_id_fk
        foreign key (category_id) references categories (id),
    constraint event_listing_categories_event_listings_id_fk
        foreign key (listing_id) references event_listings (id)
);