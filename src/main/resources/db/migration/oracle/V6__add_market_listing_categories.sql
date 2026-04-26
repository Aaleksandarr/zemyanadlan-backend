CREATE TABLE market_listing_categories (
    listing_id NUMBER(19) NOT NULL,
    category_id NUMBER(19) NOT NULL,
    CONSTRAINT market_listing_categories_pk
        PRIMARY KEY (listing_id, category_id),
    CONSTRAINT market_listing_categories_categories_id_fk
        FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT market_listing_categories_market_listings_id_fk
        FOREIGN KEY (listing_id) REFERENCES market_listings(id)
                                       );