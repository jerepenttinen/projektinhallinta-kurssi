CREATE TABLE IF NOT EXISTS image
(
    id         SERIAL PRIMARY KEY,
    image_data BYTEA NOT NULL,
    recipe_id  INT /* Temporary column for migration, to be dropped */
);

CREATE TABLE IF NOT EXISTS usr_has_image
(
    usr_id   INT REFERENCES usr (id) ON DELETE CASCADE,
    image_id INT REFERENCES image (id) ON DELETE CASCADE,
    CONSTRAINT usr_has_image_pk PRIMARY KEY (usr_id, image_id)
);

CREATE TABLE IF NOT EXISTS recipe_has_image
(
    recipe_id INT REFERENCES recipe (id) ON DELETE CASCADE,
    image_id  INT REFERENCES image (id) ON DELETE CASCADE,
    CONSTRAINT recipe_has_image_pk PRIMARY KEY (recipe_id, image_id)
);

INSERT INTO image (recipe_id, image_data, id)
SELECT recipe.id,
        recipe.recipe_img,
        nextval('public.image_id_seq')
FROM recipe;

INSERT INTO recipe_has_image (recipe_id, image_id)
SELECT recipe_id, id
FROM image;

ALTER TABLE image
    DROP COLUMN recipe_id;
