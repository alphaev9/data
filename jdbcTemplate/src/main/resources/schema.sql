-- noinspection SqlDialectInspectionForFile

create table if not exists address
(
	id int auto_increment
		primary key,
	street varchar(20) null,
	number int null
);

