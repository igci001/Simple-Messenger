ALTER TABLE "user"
    ALTER COLUMN auth_user_id SET NOT NULL;
ALTER TABLE "user"
    ALTER COLUMN last_name SET NOT NULL;

ALTER TABLE "user"
    RENAME COLUMN surname TO first_name;