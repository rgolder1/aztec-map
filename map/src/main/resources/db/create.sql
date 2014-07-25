create table team(
	id int not null,
	name varchar(32) not null,
	ground varchar(32) not null,
	league varchar(16) not null,
	latitude decimal not null,
	longitude decimal not null,
	search_term varchar(32) not null,
	label_align varchar(10) null,
	CONSTRAINT pk_team PRIMARY KEY (id)
);

create table keyword(
	id int not null,
	team_id int not null,
	word varchar(32) not null,
	CONSTRAINT pk_keyword PRIMARY KEY (id),
	CONSTRAINT fk_keyword_team FOREIGN KEY (team_id) REFERENCES team (id)
);

create table region(
	id int not null,
	name varchar(32) not null,
	latitude decimal not null,
	longitude decimal not null,
	woe_id bigint not null,
	CONSTRAINT pk_region PRIMARY KEY (id)
);

create table city(
	id int not null,
	name varchar(32) not null,
	latitude decimal not null,
	longitude decimal not null,
	CONSTRAINT pk_city PRIMARY KEY (id)
);