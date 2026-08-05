INSERT INTO doctor(id, first_name, last_name, timezone) VALUES(1, 'John', 'Doe', 'Europe/Kiev');
INSERT INTO doctor(id, first_name, last_name, timezone) VALUES(2, 'Ai', 'Bolit', 'America/New_York');

INSERT INTO patients(id, first_name, last_name) VALUES(1, 'Patient0', 'Zero');
INSERT INTO patients(id, first_name, last_name) VALUES(2, 'Patient1', 'One');
INSERT INTO patients(id, first_name, last_name) VALUES(3, 'Patient2', 'Two');

INSERT INTO visit(doctor_id, end_date_time, id, patient_id, start_date_time) VALUES(1, '2026-08-04 02:00:00-02:00', 1, 1, '2026-08-04 00:00:00-02:00');
INSERT INTO visit(doctor_id, end_date_time, id, patient_id, start_date_time) VALUES(2, '2026-08-04 02:00:00-05:00', 2, 2, '2026-08-04 00:00:00-05:00');
