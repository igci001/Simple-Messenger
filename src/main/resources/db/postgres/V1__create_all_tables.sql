CREATE TABLE "user" (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    created_date timestamp NOT NULL,
    last_modified_date timestamp NOT NULL,
    surname varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    birth_day timestamp NOT NULL,
    description varchar(255)
);

CREATE TABLE chat (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    created_date timestamp NOT NULL,
    last_modified_date timestamp NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(255),
    owner_id uuid references "user" (id)
);

CREATE TABLE chat_member (
    created_date timestamp NOT NULL,
    user_id uuid references "user"(id),
    chat_id uuid references chat(id),
    PRIMARY KEY (user_id,chat_id)
);

CREATE TABLE message (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    created_date timestamp NOT NULL,
    last_modified_date timestamp NOT NULL,
    message varchar(255) NOT NULL,
    chat_id uuid references chat(id),
    user_id uuid references "user"(id)
);