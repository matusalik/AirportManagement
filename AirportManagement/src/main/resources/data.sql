/*
INSERT INTO APP_USER (USER_LOGIN, USER_PASSWORD) VALUES ('root', 'root');
INSERT INTO APP_USER (USER_LOGIN, USER_PASSWORD) VALUES ('sysop', 'sysop');
INSERT INTO APP_USER (USER_LOGIN, USER_PASSWORD) VALUES ('admin', 'admin');

-- Lotniska (ID generowane automatycznie)
INSERT INTO AIRPORT (AIRPORT_CODE, CITY) VALUES ('KAT', 'Katowice');        -- id = 1
INSERT INTO AIRPORT (AIRPORT_CODE, CITY) VALUES ('JFK', 'New York');        -- id = 2
INSERT INTO AIRPORT (AIRPORT_CODE, CITY) VALUES ('CDG', 'Paris');           -- id = 3
INSERT INTO AIRPORT (AIRPORT_CODE, CITY) VALUES ('HND', 'Tokyo');           -- id = 4
INSERT INTO AIRPORT (AIRPORT_CODE, CITY) VALUES ('DXB', 'Dubai');           -- id = 5
INSERT INTO AIRPORT (AIRPORT_CODE, CITY) VALUES ('SYD', 'Sydney');          -- id = 6
INSERT INTO AIRPORT (AIRPORT_CODE, CITY) VALUES ('FRA', 'Frankfurt');       -- id = 7
INSERT INTO AIRPORT (AIRPORT_CODE, CITY) VALUES ('PEK', 'Beijing');         -- id = 8
INSERT INTO AIRPORT (AIRPORT_CODE, CITY) VALUES ('GRU', 'São Paulo');       -- id = 9
INSERT INTO AIRPORT (AIRPORT_CODE, CITY) VALUES ('LAX', 'Los Angeles');     -- id = 10

-- Samoloty (ID generowane automatycznie)
INSERT INTO AIRPLANE (SEAT_AMOUNT, MODEL) VALUES (180, 'Airbus A320');              -- id = 1
INSERT INTO AIRPLANE (SEAT_AMOUNT, MODEL) VALUES (160, 'Boeing 737');               -- id = 2
INSERT INTO AIRPLANE (SEAT_AMOUNT, MODEL) VALUES (396, 'Boeing 777');               -- id = 3
INSERT INTO AIRPLANE (SEAT_AMOUNT, MODEL) VALUES (500, 'Airbus A380');              -- id = 4
INSERT INTO AIRPLANE (SEAT_AMOUNT, MODEL) VALUES (100, 'Embraer E190');             -- id = 5
INSERT INTO AIRPLANE (SEAT_AMOUNT, MODEL) VALUES (90, 'Bombardier CRJ900');         -- id = 6
INSERT INTO AIRPLANE (SEAT_AMOUNT, MODEL) VALUES (280, 'Boeing 787 Dreamliner');    -- id = 7
INSERT INTO AIRPLANE (SEAT_AMOUNT, MODEL) VALUES (185, 'Airbus A321');              -- id = 8
INSERT INTO AIRPLANE (SEAT_AMOUNT, MODEL) VALUES (410, 'Boeing 747');               -- id = 9
INSERT INTO AIRPLANE (SEAT_AMOUNT, MODEL) VALUES (76, 'Embraer E175');              -- id = 10


-- Pasażerowie (ID generowane automatycznie)
INSERT INTO PASSENGER (PASSPORT_ID, NAME, SURNAME, BIRTH_DATE) VALUES ('P001', 'John', 'Doe', '1980-01-15');   -- id = 1
INSERT INTO PASSENGER (PASSPORT_ID, NAME, SURNAME, BIRTH_DATE) VALUES ('P002', 'Jane', 'Smith', '1985-03-22'); -- id = 2
INSERT INTO PASSENGER (PASSPORT_ID, NAME, SURNAME, BIRTH_DATE) VALUES ('P003', 'Robert', 'Brown', '1978-07-09'); -- id = 3
INSERT INTO PASSENGER (PASSPORT_ID, NAME, SURNAME, BIRTH_DATE) VALUES ('P004', 'Emily', 'Davis', '1990-11-05'); -- id = 4
INSERT INTO PASSENGER (PASSPORT_ID, NAME, SURNAME, BIRTH_DATE) VALUES ('P005', 'Michael', 'Wilson', '1982-05-30'); -- id = 5
INSERT INTO PASSENGER (PASSPORT_ID, NAME, SURNAME, BIRTH_DATE) VALUES ('P006', 'Sarah', 'Johnson', '1992-02-14'); -- id = 6
INSERT INTO PASSENGER (PASSPORT_ID, NAME, SURNAME, BIRTH_DATE) VALUES ('P007', 'David', 'Lee', '1975-09-23'); -- id = 7
INSERT INTO PASSENGER (PASSPORT_ID, NAME, SURNAME, BIRTH_DATE) VALUES ('P008', 'Laura', 'Martinez', '1988-06-17'); -- id = 8
INSERT INTO PASSENGER (PASSPORT_ID, NAME, SURNAME, BIRTH_DATE) VALUES ('P009', 'Thomas', 'Garcia', '1983-10-12'); -- id = 9
INSERT INTO PASSENGER (PASSPORT_ID, NAME, SURNAME, BIRTH_DATE) VALUES ('P010', 'Anna', 'Rodriguez', '1995-12-01'); -- id = 10


-- Loty (ID generowane automatycznie)
-- KAT (1) → JFK (2), Boeing 747 (9)
INSERT INTO FLIGHT (DEPARTURE_DATE, ARRIVAL_DATE, CHECK_IN, DEPARTURE_AIRPORT_ID_AIRPORT, ARRIVAL_AIRPORT_ID_AIRPORT, AIRPLANE_ID_AIRPLANE)
VALUES ('2025-05-10', '2025-05-10', '06:30:00', 1, 2, 9); -- id = 1

-- CDG (3) → FRA (7), Airbus A320 (1)
INSERT INTO FLIGHT (DEPARTURE_DATE, ARRIVAL_DATE, CHECK_IN, DEPARTURE_AIRPORT_ID_AIRPORT, ARRIVAL_AIRPORT_ID_AIRPORT, AIRPLANE_ID_AIRPLANE)
VALUES ('2025-05-11', '2025-05-11', '07:15:00', 3, 7, 1); -- id = 2

-- HND (4) → SYD (6), Boeing 787 (7)
INSERT INTO FLIGHT (DEPARTURE_DATE, ARRIVAL_DATE, CHECK_IN, DEPARTURE_AIRPORT_ID_AIRPORT, ARRIVAL_AIRPORT_ID_AIRPORT, AIRPLANE_ID_AIRPLANE)
VALUES ('2025-05-12', '2025-05-12', '08:00:00', 4, 6, 7); -- id = 3

-- DXB (5) → GRU (9), Airbus A380 (4)
INSERT INTO FLIGHT (DEPARTURE_DATE, ARRIVAL_DATE, CHECK_IN, DEPARTURE_AIRPORT_ID_AIRPORT, ARRIVAL_AIRPORT_ID_AIRPORT, AIRPLANE_ID_AIRPLANE)
VALUES ('2025-05-13', '2025-05-13', '09:00:00', 5, 9, 4); -- id = 4

-- PEK (8) → LAX (10), Boeing 777 (3)
INSERT INTO FLIGHT (DEPARTURE_DATE, ARRIVAL_DATE, CHECK_IN, DEPARTURE_AIRPORT_ID_AIRPORT, ARRIVAL_AIRPORT_ID_AIRPORT, AIRPLANE_ID_AIRPLANE)
VALUES ('2025-05-14', '2025-05-14', '05:45:00', 8, 10, 3); -- id = 5


INSERT INTO FLIGHT_PASSENGER (ID_FLIGHT, ID_PASSENGER) VALUES (1, 1);
INSERT INTO FLIGHT_PASSENGER (ID_FLIGHT, ID_PASSENGER) VALUES (1, 2);
INSERT INTO FLIGHT_PASSENGER (ID_FLIGHT, ID_PASSENGER) VALUES (1, 3);

INSERT INTO FLIGHT_PASSENGER (ID_FLIGHT, ID_PASSENGER) VALUES (2, 4);
INSERT INTO FLIGHT_PASSENGER (ID_FLIGHT, ID_PASSENGER) VALUES (2, 5);

INSERT INTO FLIGHT_PASSENGER (ID_FLIGHT, ID_PASSENGER) VALUES (3, 6);
INSERT INTO FLIGHT_PASSENGER (ID_FLIGHT, ID_PASSENGER) VALUES (3, 7);
INSERT INTO FLIGHT_PASSENGER (ID_FLIGHT, ID_PASSENGER) VALUES (3, 8);

INSERT INTO FLIGHT_PASSENGER (ID_FLIGHT, ID_PASSENGER) VALUES (4, 9);
INSERT INTO FLIGHT_PASSENGER (ID_FLIGHT, ID_PASSENGER) VALUES (4, 10);

INSERT INTO FLIGHT_PASSENGER (ID_FLIGHT, ID_PASSENGER) VALUES (5, 1);
INSERT INTO FLIGHT_PASSENGER (ID_FLIGHT, ID_PASSENGER) VALUES (5, 6);
*/

INSERT INTO AIRPORT (AIRPORT_CODE, CITY) VALUES ('KAT', 'Katowice'); 
INSERT INTO AIRPORT (AIRPORT_CODE, CITY) VALUES ('LAX', 'LosAngeles'); 
INSERT INTO AIRPLANE (SEAT_AMOUNT, MODEL) VALUES (123, 'Boeing 787'); 