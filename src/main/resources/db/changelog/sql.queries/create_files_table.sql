create table files
(
    id       bigint       not null auto_increment primary key,
    location varchar(255) not null,
    created  timestamp default current_timestamp,
    updated  timestamp default current_timestamp
)