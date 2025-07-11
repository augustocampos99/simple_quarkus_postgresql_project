CREATE SCHEMA company AUTHORIZATION postgres;

CREATE TABLE company.departments (
	id serial4 NOT NULL,
	"name" varchar(100) NOT NULL,
	description text NULL,
	CONSTRAINT departments_pk PRIMARY KEY (id)
);

CREATE TABLE company.employees (
	id serial4 NOT NULL,
	guid uuid NOT NULL,
	"name" varchar(100) NOT NULL,
	cpf varchar(11) NOT NULL,
	email varchar(100) NULL,
	phone varchar(20) NULL,
	id_department int4 NOT NULL,
	CONSTRAINT employee_pk PRIMARY KEY (id),
	CONSTRAINT employee_unique_cpf UNIQUE (cpf),
	CONSTRAINT employee_unique_guid UNIQUE (guid)
);


ALTER TABLE company.employees ADD CONSTRAINT "employee_department_FK" FOREIGN KEY (id_department) REFERENCES company.departments(id);