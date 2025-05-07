CREATE TABLE users_subscriptions
(
    user_id         BIGSERIAL NOT NULL,
    subscription_id BIGSERIAL NOT NULL,
    PRIMARY KEY (user_id, subscription_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (subscription_id) REFERENCES subscriptions (id)
)