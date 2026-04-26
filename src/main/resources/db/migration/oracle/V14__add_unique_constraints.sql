ALTER TABLE categories
ADD CONSTRAINT uq_categories_slug UNIQUE (slug);

ALTER TABLE users
    ADD CONSTRAINT uq_users_username UNIQUE (username);

ALTER TABLE users
    ADD CONSTRAINT uq_users_email UNIQUE (email);
