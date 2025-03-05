CREATE TABLE posts_tb (
    id VARCHAR PRIMARY KEY NOT NULL,
    content VARCHAR NOT NULL,
    media_url VARCHAR,
    user_id VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users_tb(id)
);