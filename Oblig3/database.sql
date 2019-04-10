DROP SCHEMA IF EXISTS oblig3 CASCADE;
CREATE SCHEMA oblig3;
SET search_path TO oblig3;

--Tabeller

CREATE TABLE Ansatt
(
  ansattId SERIAL,
  brukernavn VARCHAR(16),
  fornavn varchar(32),
  etternavn Varchar(32),
  ansdato DATE,
  stilling VARCHAR(32),
  maanedslonn INTEGER,
  avdId int,
  unique(ansattId, brukernavn),
  constraint AnsattPK PRIMARY KEY(ansattId)
);

create table Avdeling(
  avdId SERIAL,
  navn VARCHAR(32),
  Sjef INTEGER,
  unique(avdId),
  constraint AvdelingPK primary key(avdId)
 
);

alter table Ansatt
          add constraint AnsattFK foreign key (avdId) references Avdeling(avdId);
         
alter table Avdeling 
          add constraint AvdelingFK foreign key (Sjef) references Ansatt(ansattId); 

--Legger inn data i tabellene

insert into Ansatt(brukernavn, fornavn, etternavn, ansdato, stilling, maanedslonn)
values('BSI', 'Bendik', 'Simonsen', '2018-08-21', 'CEO', 1000000);

insert into Ansatt(brukernavn, fornavn, etternavn, ansdato, stilling, maanedslonn)
values('JOVO', 'Jon', 'Vollset', '2018-08-21', 'CTO', 700000);

insert into Ansatt(brukernavn, fornavn, etternavn, ansdato, stilling, maanedslonn)
values('STA', 'Stefan', 'Taranger', '2018-08-21', 'Programmer', 500000);

insert into Ansatt(brukernavn, fornavn, etternavn, ansdato, stilling, maanedslonn)
values('MOTO', 'Morten', 'Tordal', '2018-08-21', 'Vinduspusser', 500000);

insert into Avdeling(navn)
values('Styre'),
      ('Renhold');    

update Avdeling
set Sjef = 1
where avdId = 1;

update Avdeling 
set Sjef = 2
where avdId = 2;

update Ansatt
set avdId = 1
where ansattId = 1;

update Ansatt 
set avdId = 2
where ansattId = 4;

update oblig3.Ansatt 
set avdId = 2
where ansattId = 3;

update oblig3.Ansatt 
set avdId = 1
where ansattId = 2;


CREATE TABLE prosjekt(
	prosjektId SERIAL,
	navn varchar(30),
	beskrivelse varchar(150),
	constraint prosjektPK primary key (prosjektId)
);

CREATE TABLE prosjektdeltagelse(
    prosjektdeltagelseId SERIAL,
	ansattId int references oblig3.Ansatt(ansattId),
	prosjektId int references prosjekt(prosjektId),
	Rolle varchar(20) not null,
	Timer numeric,
	constraint PK_prosjektdeltagelse primary key (prosjektdeltagelseId),
	constraint PU_prosjektdeltagelseunik unique (AnsattId, ProsjektId)
);

create view prosjektInfo as
select navn, beskrivelse, fornavn, etternavn, rolle, timer, sum(timer) as totaltAntallTimerForProsjekt
from prosjekt p, prosjektdeltagelse pd, oblig3.ansatt a
where p.prosjektId = pd.prosjektId and pd.ansattId = a.ansattId
group by p.prosjektId, a.fornavn, a.etternavn, pd.rolle, pd.timer;