CREATE TABLE event_listing_categories (
    listing_id NUMBER(19) NOT NULL,
    category_id NUMBER(19) NOT NULL,
    CONSTRAINT event_listing_categories_pk
        PRIMARY KEY (listing_id, category_id),
    CONSTRAINT event_listing_categories_categories_id_fk
        FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT event_listing_categories_event_listings_id_fk
        FOREIGN KEY (listing_id) REFERENCES event_listings(id)
                                      );