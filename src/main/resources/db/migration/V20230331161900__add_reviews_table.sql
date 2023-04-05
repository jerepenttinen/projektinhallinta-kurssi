CREATE TABLE review
(
    id          SERIAL PRIMARY KEY,
    recipe_id   INT REFERENCES recipe (id),
    reviewer_id INT REFERENCES usr (id),
    created_at  timestamp default now(),
    is_upvote   BOOLEAN NOT NULL,
    content     TEXT    NOT NULL
);