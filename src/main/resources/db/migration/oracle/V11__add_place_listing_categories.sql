CREATE TABLE place_listing_categories (
    listing_id NUMBER(19) NOT NULL,
    category_id NUMBER(19) NOT NULL,
    CONSTRAINT place_listing_categories_pk
        PRIMARY KEY (listing_id, category_id),
    CONSTRAINT place_listing_categories_categories_id_fk
        FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT place_listing_categories_place_listings_id_fk
        FOREIGN KEY (listing_id) REFERENCES place_listings(id));