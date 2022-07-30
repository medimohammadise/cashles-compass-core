--liquibase formatted sql

--changeset sara:1
create table usergroup(
  id int  primary key,
  code varchar(3),
  name varchar(50)

);


--changeset sara:2

alter table usergroup add column email varchar(255)

--rollback alter table usergroup drop column email



