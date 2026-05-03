ALTER TABLE community_posts
ADD (
    post_type VARCHAR2(20) NOT NULL,
    views_count NUMBER(10) DEFAULT 0 NOT NULL,
    comments_count NUMBER(10) DEFAULT 0 NOT NULL,
    likes_count NUMBER(10) DEFAULT 0 NOT NULL,
    thumbnail_url VARCHAR2(255)
);