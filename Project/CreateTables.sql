/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  r0661567
 * Created: 13-nov-2019
 */
 
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
    moment varchar2(50),
    primary key(serienummer, moment)
);

create table docenten(
    naam varchar2(20) references gebruikers primary key,
    opleiding varchar2(10)
);

insert into gebruikers values ('Jantje', 'Jantje');
insert into gebruikers values ('Herman', 'Herman');
insert into gebruikers values ('Jonas', 'Jonas');
insert into gebruikers values ('Ben', 'Ben');
insert into gebruikers values ('Ivens', 'Ivens');
insert into gebruikers values ('VRIJ', 'ISGEENECHTEGEBRUIKER');

insert into groepen values ('Jantje', 'studenten');
insert into groepen values ('Herman', 'docenten');
insert into groepen values ('Jonas', 'studenten');
insert into groepen values ('Ben', 'externen');
insert into groepen values ('Ivens', 'docenten');

insert into docenten values ('Herman', 'ict');
insert into docenten values ('Ivens', 'em');

insert into machines values ('Trekbank', 'Trekbank voor het labo sterkteleer', 'A008', 'em', 465132, 75000, 120);
insert into machines values ('PC Win10', 'PC met windows 10 in computerlokaal', 'A213', 'ict', 31501, 1100, 40);
insert into machines values ('PC Win10', 'PC met windows 10 in computerlokaal', 'A213', 'ict', 31502, 1100, 40);
insert into machines values ('PC Win10', 'PC met windows 10 in computerlokaal', 'A213', 'ict', 31503, 1100, 40);
insert into machines values ('PC Win10', 'PC met windows 10 in computerlokaal', 'A213', 'ict', 31504, 1100, 40);
insert into machines values ('PC Ubuntu', 'PC met ubuntu 18.04 en quadro gpu in computerlokaal', 'A212', 'ict', 1458, 1800, 60);
insert into machines values ('PC Ubuntu', 'PC met ubuntu 18.04 en quadro gpu in computerlokaal', 'A212', 'ict', 1459, 1800, 60);
insert into machines values ('PC Win10', 'PC met windows 10 in computerlokaal', 'A213', 'ict', 31120, 1100, 40);
insert into machines values ('PC Win10', 'PC met windows 10 in computerlokaal', 'A213', 'ict', 31121, 1100, 40);

insert into reservaties values (31120, 'Jonas', 'Maandag voormiddag');
insert into reservaties values (31120, 'VRIJ', 'Maandag namiddag');
insert into reservaties values (31120, 'VRIJ', 'Dinsdag voormiddag');
insert into reservaties values (31120, 'Ben', 'Dinsdag namiddag');