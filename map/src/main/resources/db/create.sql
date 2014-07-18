create table team(
	id int not null,
	name varchar(32) not null,
	ground varchar(32) not null,
	league varchar(16) not null,
	latitude decimal not null,
	longitude decimal not null,
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

--insert into team values(1, 'Liverpool', 'Anfield', 'Premiership', 53.4308014, -2.9611001);
--insert into team values(2, 'Manchester United', 'Old Trafford', 'Premiership', 53.476498, -2.282984);
--
--insert into keyword values(1, 1, 'liverpool');
--insert into keyword values(2, 1, 'liverpoolfc');
--insert into keyword values(3, 1, 'lfc');
--insert into keyword values(4, 2, 'manchester united');
--insert into keyword values(5, 2, 'manutd');
--insert into keyword values(7, 2, 'man utd');
--insert into keyword values(8, 2, 'manchesterutd');
--insert into keyword values(9, 2, 'manchester utd');