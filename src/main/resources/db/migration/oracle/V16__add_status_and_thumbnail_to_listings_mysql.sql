-- ===============================

-- MARKET LISTINGS

-- ===============================

ALTER TABLE market_listings

    ADD (

    status VARCHAR2(20) DEFAULT 'DRAFT' NOT NULL,

    thumbnail_url VARCHAR2(255)

);



-- ===============================

-- CRAFT LISTINGS

-- ===============================

ALTER TABLE craft_listings

    ADD (

    status VARCHAR2(20) DEFAULT 'DRAFT' NOT NULL,

    thumbnail_url VARCHAR2(255)

);



-- ===============================

-- PLACE LISTINGS

-- ===============================

ALTER TABLE place_listings

    ADD (

    status VARCHAR2(20) DEFAULT 'DRAFT' NOT NULL,

    thumbnail_url VARCHAR2(255)

);



-- ===============================

-- EVENT LISTINGS

-- ===============================

ALTER TABLE event_listings

    ADD (

    status VARCHAR2(20) DEFAULT 'DRAFT' NOT NULL,

    thumbnail_url VARCHAR2(255)

);