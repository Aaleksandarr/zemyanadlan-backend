CREATE TABLE post_categories (
    post_id NUMBER(19) NOT NULL,
    category_id NUMBER(19) NOT NULL,
    CONSTRAINT post_categories_pk PRIMARY KEY (post_id, category_id),
    CONSTRAINT post_categories_categories_id_fk
        FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT post_categories_community_posts_id_fk
        FOREIGN KEY (post_id) REFERENCES community_posts(id)
                             );