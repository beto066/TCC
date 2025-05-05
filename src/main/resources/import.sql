INSERT INTO Users(name, email, password, created_at) VALUES ('Jon Snow', 'jon@gmail.com', 'M4JbgkV7ojGWbcX0Z7Eqz2OU/3nKfMQ5jjhn0b4x2H5LXDa1/nuYto96t0JoMnHmG/tt9WyIrv7hXwYmx7aIAg==', '10-18-2022');
INSERT INTO Users(name, email, password, created_at) VALUES ('Vegeta', 'vegeta@gmail.com', 'M4JbgkV7ojGWbcX0Z7Eqz2OU/3nKfMQ5jjhn0b4x2H5LXDa1/nuYto96t0JoMnHmG/tt9WyIrv7hXwYmx7aIAg==', '10-18-2022');
INSERT INTO Users(name, email, password, created_at) VALUES ('The rock', 'therock@gmail.com', 'M4JbgkV7ojGWbcX0Z7Eqz2OU/3nKfMQ5jjhn0b4x2H5LXDa1/nuYto96t0JoMnHmG/tt9WyIrv7hXwYmx7aIAg==', '10-18-2022');

INSERT INTO Therapists(id) VALUES (1);
INSERT INTO Therapists(id) VALUES (3);

INSERT INTO NoteTableValue(label, value, active, therapist_id) VALUES ('SU', 'SUCCES', true, 1);
INSERT INTO NoteTableValue(label, value, active, therapist_id) VALUES ('ST', 'Succes with tip', true, 1);
INSERT INTO NoteTableValue(label, value, active, therapist_id) VALUES ('ER', 'Error', true, 1);
INSERT INTO NoteTableValue(label, value, active, therapist_id) VALUES ('SU', 'SUCCES', true, 3);
INSERT INTO NoteTableValue(label, value, active, therapist_id) VALUES ('ST', 'Succes with tip', true, 3);
INSERT INTO NoteTableValue(label, value, active, therapist_id) VALUES ('ER', 'Error', true, 3);

INSERT INTO Patient(birth, name, tratment_started_at, created_at) VALUES ('10-18-2022', 'jose', '10-18-2023', '11-01-2024');
INSERT INTO Patient(birth, name, tratment_started_at, created_at) VALUES ('10-18-2022', 'joilson', '10-18-2023', '11-01-2024');
INSERT INTO Patient(birth, name, tratment_started_at, created_at) VALUES ('10-18-2022', 'josue', '10-18-2023', '11-01-2024');

INSERT INTO therapist_patients(therapist_id, patient_id) VALUES (1, 1);
INSERT INTO therapist_patients(therapist_id, patient_id) VALUES (1, 2);
INSERT INTO therapist_patients(therapist_id, patient_id) VALUES (1, 3);

INSERT INTO Roles(user_id, role) VALUES (1, 1);
INSERT INTO Roles(user_id, role) VALUES (2, 2);
INSERT INTO Roles(user_id, role) VALUES (3, 1);

INSERT INTO Notes(author_id, patient_id, program, type, level, created_at) VALUES (1, 2, 0, 0, 0, NOW());
INSERT INTO Notes(author_id, patient_id, program, type, level, created_at) VALUES (2, 1, 0, 0, 0, NOW());
INSERT INTO Notes(author_id, patient_id, program, type, level, created_at) VALUES (3, 2, 0, 0, 0, NOW());
INSERT INTO Notes(author_id, patient_id, program, type, level, created_at) VALUES (1, 1, 0, 2, 0, NOW());
INSERT INTO Notes(author_id, patient_id, program, type, level, created_at) VALUES (1, 3, 0, 2, 0, NOW());
INSERT INTO Notes(author_id, patient_id, program, type, level, created_at) VALUES (1, 3, 0, 2, 0, NOW());
INSERT INTO Notes(author_id, patient_id, program, type, level, created_at) VALUES (1, 3, 0, 1, 0, NOW());
INSERT INTO Notes(author_id, patient_id, program, type, level, created_at) VALUES (1, 3, 0, 1, 0, NOW());
INSERT INTO Notes(author_id, patient_id, program, type, level, created_at) VALUES (1, 3, 0, 1, 0, NOW());

INSERT INTO Notepads(id, title, body) VALUES (1, 'anotação do dia 11/11/2023', '["Hello mundo"]');
INSERT INTO Notepads(id, title, body) VALUES (2, 'anotação do dia 11/11/2023', '["Ele demonstra comportamentos impulsivos quando apresentado à alguns brinquedos satisfatórios.", "Ele parece muito alegre!"]');
INSERT INTO Notepads(id, title, body) VALUES (3, 'anotação do dia 11/11/2023', '["E nessa loucura", "de negar o meu desejo", "eu te quero mais que tudo", "eu preciso do seu beijo", "eu entrego a minha vida", "pra vc fazer oq quiser de mim."]');

INSERT INTO Note_Trainings(id) VALUES (4);
INSERT INTO Note_Trainings(id) VALUES (5);
INSERT INTO Note_Trainings(id) VALUES (6);

INSERT INTO Note_Tables(id) VALUES (7);
INSERT INTO Note_Tables(id) VALUES (8);
INSERT INTO Note_Tables(id) VALUES (9);

INSERT INTO Mapped_Training_Results(position, result, training_id) VALUES (1, 0, 4);
INSERT INTO Mapped_Training_Results(position, result, training_id) VALUES (2, 1, 4);
INSERT INTO Mapped_Training_Results(position, result, training_id) VALUES (3, 2, 4);
INSERT INTO Mapped_Training_Results(position, result, training_id) VALUES (1, 0, 5);
INSERT INTO Mapped_Training_Results(position, result, training_id) VALUES (2, 1, 5);
INSERT INTO Mapped_Training_Results(position, result, training_id) VALUES (1, 0, 6);
INSERT INTO Mapped_Training_Results(position, result, training_id) VALUES (2, 1, 6);
INSERT INTO Mapped_Training_Results(position, result, training_id) VALUES (3, 2, 6);
