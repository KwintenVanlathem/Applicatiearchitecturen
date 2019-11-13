# Applicatiearchitecturen

## Paginas

### Overzicht:
Lijst van machines, bevat telkens:
* naam
* omschrijving
* lokaal
* knop detail pagina

if groep == docent
  knop voeg machine toe

---
### Detail:
Alle info van machine:
* naam
* omschrijving
* lokaal
* opleiding
* serienummer
* aankoopprijs
* huurprijs  
knop reserveeroverzicht  
if groep == docent en opleiding docent == opleiding machine
  knop wijzigen

---
### Reserveeroverzicht docent:
Lijst met alle momenten van machine, bevat telkens:
* moment
* status
* if status == gereserveerd
  user die heeft gereserveerd

if opleiding docent == opleiding machine
  knop voeg moment toe

----
### Reserveeroverzicht student en extern:
Lijst met beschikbare momenten van machine, bevat telkens:
* moment
* knop reserveer

---
### Reserveer enkel voor extern:
Prijs van de reservatie  
knop cancel  
knop confirm

---
### Voeg machine toe enkel voor docent:
blanco invulveldjes voor alle info delen (behalve opleiding)  
knop confirm

---
### Wijzig machine toe enkel voor docent:
[prefilled](https://stackoverflow.com/questions/30516391/html-input-already-filled-in-text) invulveldjes voor alle info delen (behalve opleiding????)  
knop confirm

---
### Voeg moment toe enkel voor docent:
blanco invulveldje voor alle moment  
knop confirm
