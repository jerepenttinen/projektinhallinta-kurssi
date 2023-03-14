ALTER TABLE usr
    DROP COLUMN created_at;

ALTER TABLE usr
    ADD COLUMN created_at timestamp NOT NULL default now() ;
    
