CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY               NOT NULL,
    username    TEXT                                NOT NULL,
    email       TEXT                                NOT NULL,
    lastname    TEXT                                NOT NULL,
    middlename  TEXT,
    firstname   TEXT                                NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE subscriptions
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    service_name TEXT                  NOT NULL
);