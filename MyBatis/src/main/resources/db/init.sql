drop table if exists mammal;

create table mammal (
	id integer primary key, 
	age integer
);

drop table if exists human;

create table human (
	id integer primary key, 
	name varchar(50)
);

drop table if exists gender;

create table gender (
	id integer primary key, 
	gender varchar(50)
);