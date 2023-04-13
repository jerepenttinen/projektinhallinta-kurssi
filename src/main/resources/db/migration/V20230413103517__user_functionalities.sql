DROP TABLE usr_has_image;

ALTER TABLE usr
    ADD COLUMN image int null references image(id),
    ADD COLUMN description text;