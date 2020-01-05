-- Databank: Oracle 11g

-- Tabellen aanmaken
drop table docenten purge;
drop table reservaties purge;
drop table machines purge;
drop table groepen purge;
drop table gebruikers purge;

create table gebruikers(
    gebruikersnaam varchar2(20) primary key,
    paswoord varchar2(20)
);

create table groepen(
    gebruikersnaam varchar2(20)references gebruikers primary key,
    groep varchar2(20)
);

create table machines(
    naam varchar2(20),
    omschrijving varchar2(150),
    lokaal varchar2(5),
    opleiding varchar2(10),
    serienummer int primary key,
    aankoopprijs int,
    huurprijs int
);

create table reservaties(
    serienummer int references machines,
    gebruiker varchar2(20) references gebruikers,
    moment date,
    primary key(serienummer, moment)
);

create table docenten(
    naam varchar2(20) references gebruikers primary key,
    opleiding varchar2(10)
);

-- Tabellen vullen met inserts
insert into gebruikers values ('Jantje', 'Jantje');
insert into gebruikers values ('Herman', 'Herman');
insert into gebruikers values ('Jonas', 'Jonas');
insert into gebruikers values ('Ben', 'Ben');
insert into gebruikers values ('Ivens', 'Ivens');

insert into groepen values ('Jantje', 'studenten');
insert into groepen values ('Herman', 'docenten');
insert into groepen values ('Jonas', 'studenten');
insert into groepen values ('Ben', 'externen');
insert into groepen values ('Ivens', 'docenten');

insert into docenten values ('Herman', 'ict');
insert into docenten values ('Ivens', 'em');

insert into machines values ('Trekbank', 'Trekbank voor het labo sterkteleer', 'A008', 'em', 465132, 75000, 120);
insert into machines values ('PC Ubuntu', 'PC met ubuntu 18.04 en quadro gpu in computerlokaal', 'A212', 'ict', 1458, 1800, 60);
insert into machines values ('PC Win10', 'PC met windows 10 in computerlokaal', 'A213', 'ict', 31120, 1100, 40);


insert into reservaties values (465132, NULL, to_date('2020/01/05 10:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (465132, 'Jonas', to_date('2020/01/05 11:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (465132, 'Jonas', to_date('2020/01/05 12:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/05 10:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, 'Herman', to_date('2020/01/05 11:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, 'Herman', to_date('2020/01/05 12:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/05 13:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, 'Ben', to_date('2020/01/05 14:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/05 15:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (31120, NULL, to_date('2020/01/05 13:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (31120, 'Jantje', to_date('2020/01/05 14:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (31120, NULL, to_date('2020/01/05 15:00:00','yyyy/mm/dd hh24:mi:ss'));

insert into reservaties values (1458, NULL, to_date('2020/01/06 10:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, 'Jonas', to_date('2020/01/06 11:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, 'Herman', to_date('2020/01/06 12:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/06 13:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, 'Jantje', to_date('2020/01/06 14:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/06 15:00:00','yyyy/mm/dd hh24:mi:ss'));

insert into reservaties values (1458, 'Herman', to_date('2020/01/20 10:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/20 11:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, 'Ben', to_date('2020/01/20 12:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, 'Ben', to_date('2020/01/20 13:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, 'Jantje', to_date('2020/01/20 14:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, 'Jonas', to_date('2020/01/20 15:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/21 10:00:00','yyyy/mm/dd hh24:mi:ss'));

insert into reservaties values (465132, NULL, to_date('2020/01/22 13:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (465132, 'Ivens', to_date('2020/01/22 14:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (465132, 'Ivens', to_date('2020/01/22 15:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (465132, 'Ivens', to_date('2020/01/22 16:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (465132, NULL, to_date('2020/01/22 17:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/22 10:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, 'Jonas', to_date('2020/01/22 12:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/22 13:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/22 14:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (31120, 'Jantje', to_date('2020/01/22 10:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (31120, 'Ben', to_date('2020/01/22 12:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (31120, NULL, to_date('2020/01/22 13:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (31120, 'Ben', to_date('2020/01/22 14:00:00','yyyy/mm/dd hh24:mi:ss'));

insert into reservaties values (1458, NULL, to_date('2020/01/23 16:00:00','yyyy/mm/dd hh24:mi:ss'));

insert into reservaties values (1458, NULL, to_date('2020/01/24 09:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/24 10:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/24 11:00:00','yyyy/mm/dd hh24:mi:ss'));

insert into reservaties values (465132, NULL, to_date('2020/01/25 13:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (465132, 'Ivens', to_date('2020/01/25 14:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (465132, 'Ivens', to_date('2020/01/25 15:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (465132, 'Ivens', to_date('2020/01/25 16:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (465132, NULL, to_date('2020/01/25 17:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/25 13:00:00','yyyy/mm/dd hh24:mi:ss'));

insert into reservaties values (1458, NULL, to_date('2020/01/27 10:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/27 14:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/27 15:00:00','yyyy/mm/dd hh24:mi:ss'));
insert into reservaties values (1458, NULL, to_date('2020/01/27 16:00:00','yyyy/mm/dd hh24:mi:ss'));

insert into reservaties values (1458, NULL, to_date('2020/01/28 10:00:00','yyyy/mm/dd hh24:mi:ss'));