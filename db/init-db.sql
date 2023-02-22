

CREATE TABLE IF NOT EXISTS ingredient (
    id SERIAL PRIMARY KEY
    , ingredient_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS usr  (
    id SERIAL PRIMARY KEY
    , user_name TEXT NOT NULL
    , created_at TIME NOT NULL
);

CREATE TABLE IF NOT EXISTS recipe (
    id SERIAL PRIMARY KEY
    , recipe_name TEXT NOT NULL
    , creator_id INT NOT NULL
    , recipe_instruction TEXT[] NOT NULL
    , recipe_img BYTEA NOT NULL  /* Postgresql blob type */
    , CONSTRAINT fk_recipe FOREIGN KEY (creator_id) REFERENCES usr(id)
);

CREATE TABLE IF NOT EXISTS food_group (
    id SERIAL PRIMARY KEY
    , food_group_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS recipe_collection (
    id SERIAL PRIMARY KEY
    , recipe_collection_name TEXT NOT NULL
    , creator_id INT NOT NULL
    , CONSTRAINT fk_recipe FOREIGN KEY (creator_id) REFERENCES usr(id)
);

CREATE TABLE IF NOT EXISTS recipe_has_ingredients (
    recipe_id INT
    , ingredient_id INT
    , PRIMARY KEY (recipe_id, ingredient_id)
    , CONSTRAINT fk_recipe FOREIGN KEY(recipe_id) REFERENCES recipe(id)
    , CONSTRAINT fk_ingredient FOREIGN KEY(ingredient_id) REFERENCES ingredient(id)
);

CREATE TABLE IF NOT EXISTS recipe_has_food_groups (
    recipe_id INT
    , food_group_id INT
    , PRIMARY KEY (recipe_id, food_group_id)
    , CONSTRAINT fk_recipe FOREIGN KEY (recipe_id) REFERENCES recipe(id)
    , CONSTRAINT fk_food_group FOREIGN KEY (food_group_id) REFERENCES food_group(id)
);

CREATE TABLE IF NOT EXISTS collection_has_recipes (
    collection_id INT
    , recipe_id INT
    , PRIMARY KEY (collection_id, recipe_id)
    , CONSTRAINT fk_recipe FOREIGN KEY (recipe_id) REFERENCES recipe(id)
    , CONSTRAINT fk_recipe_collection FOREIGN KEY (collection_id) REFERENCES recipe_collection(id)
);

CREATE TABLE IF NOT EXISTS login_user(
    id SERIAL PRIMARY KEY
    , login_user_name TEXT NOT NULL
    , login_user_password TEXT NOT NULL
    , login_user_firstname TEXT NOT NULL
    , login_user_lastname TEXT NOT NULL
    , user_profile INT NOT NULL UNIQUE REFERENCES usr(id)
    , user_email TEXT NOT NULL
);
