CREATE TABLE post_hashtags (
    post_id VARCHAR NOT NULL,
    hashtag VARCHAR NOT NULL,
    CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES posts_tb(id)
);