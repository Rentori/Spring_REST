create table events
(
    id      bigint not null auto_increment,
    user_id bigint not null,
    file_id bigint not null,
    created timestamp default current_timestamp,
    updated timestamp default current_timestamp,

    primary key (id),
    foreign key (user_id)
        references users (id)
        ON UPDATE CASCADE,
    foreign key (file_id)
        references files (id)
        ON DELETE CASCADE
)