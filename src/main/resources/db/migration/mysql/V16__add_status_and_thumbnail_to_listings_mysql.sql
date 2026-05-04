-- ===============================

-- MARKET LISTINGS

-- ===============================

ALTER TABLE market_listings

    ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',

    ADD COLUMN thumbnail_url VARCHAR(255);



-- ===============================

-- CRAFT LISTINGS

-- ===============================

ALTER TABLE craft_listings

    ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',

    ADD COLUMN thumbnail_url VARCHAR(255);



-- ===============================

-- PLACE LISTINGS

-- ===============================

ALTER TABLE place_listings

    ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',

    ADD COLUMN thumbnail_url VARCHAR(255);



-- ===============================

-- EVENT LISTINGS

-- ===============================

ALTER TABLE event_listings

    ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',

    ADD COLUMN thumbnail_url VARCHAR(255);