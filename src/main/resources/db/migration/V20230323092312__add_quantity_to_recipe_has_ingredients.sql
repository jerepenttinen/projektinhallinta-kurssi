ALTER TABLE recipe_has_ingredients
    ADD COLUMN quantity TEXT;

UPDATE recipe_has_ingredients
SET quantity = '';

ALTER TABLE recipe_has_ingredients
    ALTER COLUMN quantity SET NOT NULL;
