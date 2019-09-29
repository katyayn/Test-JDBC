CREATE DATABASE IF NOT EXISTS jdbc_test;

USE jdbc_test;

CREATE TABLE IF NOT EXISTS test1
(
	te1_id bigint auto_increment not null,
	te1_name varchar(30) not null,
	primary key(te1_id)
)
;