INSERT INTO SPORT (naziv) VALUES ('Nogomet');
INSERT INTO SPORT (naziv) VALUES ('Košarka');
INSERT INTO SPORT (naziv) VALUES ('Odbojka');
INSERT INTO SPORT (naziv) VALUES ('Tenis');
INSERT INTO SPORT (naziv) VALUES ('Rukomet');
INSERT INTO SPORT (naziv) VALUES ('Vaterpolo');
INSERT INTO SPORT (naziv) VALUES ('Stolni tenis');
INSERT INTO SPORT (naziv) VALUES ('Badminton');
INSERT INTO SPORT (naziv) VALUES ('Padel');
INSERT INTO SPORT (naziv) VALUES ('Squash');

INSERT INTO POZICIJA (naziv) VALUES ('Napad');
INSERT INTO POZICIJA (naziv) VALUES ('Obrana');
INSERT INTO POZICIJA (naziv) VALUES ('Vratar');
INSERT INTO POZICIJA (naziv) VALUES ('Centar');
INSERT INTO POZICIJA (naziv) VALUES ('Razigravač');
INSERT INTO POZICIJA (naziv) VALUES ('Lijevo krilo');
INSERT INTO POZICIJA (naziv) VALUES ('Desno krilo');
INSERT INTO POZICIJA (naziv) VALUES ('Pivot');
INSERT INTO POZICIJA (naziv) VALUES ('Bloker');
INSERT INTO POZICIJA (naziv) VALUES ('Libero');

INSERT INTO UTAKMICA (lokacija, datum_vrijeme, kapacitet, trenutno_prijavljenih, sport_id) VALUES ('Sportski centar Šalata', '2026-04-17T19:00:00', 14, 4, 1);
INSERT INTO UTAKMICA (lokacija, datum_vrijeme, kapacitet, trenutno_prijavljenih, sport_id) VALUES ('Igrališta Jarun', '2026-04-24T17:00:00', 10, 1, 1);
INSERT INTO UTAKMICA (lokacija, datum_vrijeme, kapacitet, trenutno_prijavljenih, sport_id) VALUES ('Košarkaški centar Kranjčar', '2026-04-19T10:00:00', 6, 2, 2);
INSERT INTO UTAKMICA (lokacija, datum_vrijeme, kapacitet, trenutno_prijavljenih, sport_id) VALUES ('Teniski klub Maksimir', '2026-04-22T09:00:00', 4, 1, 4);
INSERT INTO UTAKMICA (lokacija, datum_vrijeme, kapacitet, trenutno_prijavljenih, sport_id) VALUES ('Sportska dvorana Trešnjevka', '2026-05-10T18:00:00', 12, 1, 5);
INSERT INTO UTAKMICA (lokacija, datum_vrijeme, kapacitet, trenutno_prijavljenih, sport_id) VALUES ('Bazen Mladost na Savi', '2026-05-12T20:00:00', 14, 1, 6);
INSERT INTO UTAKMICA (lokacija, datum_vrijeme, kapacitet, trenutno_prijavljenih, sport_id) VALUES ('SC Velesajam', '2026-05-15T16:00:00', 2, 1, 7);
INSERT INTO UTAKMICA (lokacija, datum_vrijeme, kapacitet, trenutno_prijavljenih, sport_id) VALUES ('Zagrebački velesajam - Paviljon', '2026-05-16T17:00:00', 4, 0, 8);
INSERT INTO UTAKMICA (lokacija, datum_vrijeme, kapacitet, trenutno_prijavljenih, sport_id) VALUES ('Padel Centar Novi Zagreb', '2026-05-20T19:00:00', 4, 1, 9);
INSERT INTO UTAKMICA (lokacija, datum_vrijeme, kapacitet, trenutno_prijavljenih, sport_id) VALUES ('Squash Club Vrapče', '2026-05-21T18:00:00', 2, 0, 10);

INSERT INTO PRIJAVA (ime_igraca, status, utakmica_id, pozicija_id) VALUES ('Ivan Horvat', 'POTVRDENA', 1, 1);
INSERT INTO PRIJAVA (ime_igraca, status, utakmica_id, pozicija_id) VALUES ('Marko Kovač', 'POTVRDENA', 1, 1);
INSERT INTO PRIJAVA (ime_igraca, status, utakmica_id, pozicija_id) VALUES ('Petra Novak', 'POTVRDENA', 1, 2);
INSERT INTO PRIJAVA (ime_igraca, status, utakmica_id, pozicija_id) VALUES ('Luka Perić', 'POTVRDENA', 1, 3);

INSERT INTO PRIJAVA (ime_igraca, status, utakmica_id, pozicija_id) VALUES ('Maja Blažević', 'POTVRDENA', 2, 1);

INSERT INTO PRIJAVA (ime_igraca, status, utakmica_id, pozicija_id) VALUES ('Ivan Horvat', 'LISTA_CEKANJA', 3, 5);
INSERT INTO PRIJAVA (ime_igraca, status, utakmica_id, pozicija_id) VALUES ('Marko Kovač', 'POTVRDENA', 3, 4);

INSERT INTO PRIJAVA (ime_igraca, status, utakmica_id, pozicija_id) VALUES ('Petra Novak', 'POTVRDENA', 4, null);

INSERT INTO PRIJAVA (ime_igraca, status, utakmica_id, pozicija_id) VALUES ('Domagoj Babić', 'POTVRDENA', 5, 8);
INSERT INTO PRIJAVA (ime_igraca, status, utakmica_id, pozicija_id) VALUES ('Karlo Knez', 'POTVRDENA', 6, 3);
INSERT INTO PRIJAVA (ime_igraca, status, utakmica_id, pozicija_id) VALUES ('Ana Jurić', 'POTVRDENA', 7, null);
INSERT INTO PRIJAVA (ime_igraca, status, utakmica_id, pozicija_id) VALUES ('Stjepan Božić', 'POTVRDENA', 9, 2);
