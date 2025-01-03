-- keine groß kleinschreiung
CREATE EXTENSION IF NOT EXISTS citext;

create table auth_users(
                      username citext not null primary key,
                      password varchar(500) not null,
                      created_date timestamp NOT NULL,
                      last_modified_date timestamp NOT NULL
);

ALTER TABLE "user"
    ADD COLUMN auth_user_id citext references auth_users(username);
