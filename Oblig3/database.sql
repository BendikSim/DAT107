DROP SCHEMA IF EXISTS oblig3 CASCADE;
CREATE SCHEMA oblig3;
SET serach_path TO oblig3


CREATE TABLE Ansatt (
    ansattId SERIAL,
    brukernavn VARCHAR(16),
    fornavn VARCHAR(32),
    etternavn VARCHAR(32),
    ansettelsesdato DATE,
    stilling VARCHAR(32),
    maanedslonn INTEGER,
    
    UNIQUE (AnsattID, Brukernavn)
    
    PRIMARY KEY (AnsattId)
    
);

insert into Ansatt(brukernavn, fornavn, etternavn, ansdato, stilling, maanedslonn)
values('BS', 'Bendik', 'Simonsen', '2018-08-21', 'CEO', 1000000);

insert into Ansatt(brukernavn, fornavn, etternavn, ansdato, stilling, maanedslonn)
values('JV', 'Jon', 'Vollset', '2018-08-21', 'CTO', 700000);

insert into Ansatt(brukernavn, fornavn, etternavn, ansdato, stilling, maanedslonn)
values('ST', 'Stefan', 'Taranger', '2018-08-21', 'Programmer', 500000);

select * from Ansatt;
    
