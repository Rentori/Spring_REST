CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    foreign key (user_id)
        references users (id)
        on update cascade,
    foreign key (role_id)
        references roles (id)
        on update cascade
)