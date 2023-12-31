INSERT INTO Users(name, email, password, created_at) VALUES ('Jon Snow', 'jon@gmail.com', 'M4JbgkV7ojGWbcX0Z7Eqz2OU/3nKfMQ5jjhn0b4x2H5LXDa1/nuYto96t0JoMnHmG/tt9WyIrv7hXwYmx7aIAg==', '10-18-2022');
INSERT INTO Users(name, email, password, created_at) VALUES ('Vegeta', 'vegeta@gmail.com', 'M4JbgkV7ojGWbcX0Z7Eqz2OU/3nKfMQ5jjhn0b4x2H5LXDa1/nuYto96t0JoMnHmG/tt9WyIrv7hXwYmx7aIAg==', '10-18-2022');
INSERT INTO Users(name, email, password, created_at) VALUES ('The rock', 'therock@gmail.com', 'M4JbgkV7ojGWbcX0Z7Eqz2OU/3nKfMQ5jjhn0b4x2H5LXDa1/nuYto96t0JoMnHmG/tt9WyIrv7hXwYmx7aIAg==', '10-18-2022');

INSERT INTO Therapist(id) VALUES (1);
INSERT INTO Therapist(id) VALUES (3);

INSERT INTO Patient(birth, name, tratment_started_at, created_at) VALUES ('10-18-2022', 'jose', '10-18-2023', '10-18-2023');
INSERT INTO Patient(birth, name, tratment_started_at, created_at) VALUES ('10-18-2022', 'joilson', '10-18-2023', '10-18-2023');
INSERT INTO Patient(birth, name, tratment_started_at, created_at) VALUES ('10-18-2022', 'josue', '10-18-2023', '10-18-2023');

INSERT INTO therapist_patient(therapist_id, patient_id) VALUES (1, 1);
INSERT INTO therapist_patient(therapist_id, patient_id) VALUES (1, 2);
INSERT INTO therapist_patient(therapist_id, patient_id) VALUES (1, 3);

INSERT INTO Roles(user_id, role) VALUES (1, 1);
INSERT INTO Roles(user_id, role) VALUES (2, 2);
INSERT INTO Roles(user_id, role) VALUES (3, 1);