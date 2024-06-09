-- liquibase formatted sql
-- changeset paglebov_1@edu.hse.ru:1 -comment:Comment for user
create table if not exists users
(
      id       int          generated by default as identity primary key
    , nickname varchar(50)         not null
    , email    varchar(100) unique not null
    , password varchar(255)        not null
    , created  timestamp    default current_timestamp
);
    -- rollback delete users;