/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  r0661567
 * Created: 13-nov-2019
 */
drop table machine purge;
drop table groepen purge;
drop table gebruikers purge;
drop table docenten purge;
drop table reservaties purge;


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