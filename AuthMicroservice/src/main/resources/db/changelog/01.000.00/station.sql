-- liquibase formatted sql
-- changeset ddrakhmanov@edu.hse.ru:1 -comment:Comment for user
create table if not exists station
(
    id      integer generated by default as identity primary key,
    station varchar(50) not null
);
-- rollback delete users;
