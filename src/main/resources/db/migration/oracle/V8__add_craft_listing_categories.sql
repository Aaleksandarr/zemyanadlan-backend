CREATE TABLE craft_listing_categories (
    listing_id NUMBER(19) NOT NULL,
    category_id NUMBER(19) NOT NULL,
    CONSTRAINT craft_listing_categories_pk
        PRIMARY KEY (listing_id, category_id),
    CONSTRAINT craft_listing_categories_categories_id_fk
        FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT craft_listing_categories_craft_listings_id_fk
        FOREIGN KEY (listing_id) REFERENCES craft_listings(id));