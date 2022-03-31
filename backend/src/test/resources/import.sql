/* DUMMY DATA FOR TESTS */

/* Terms */
INSERT INTO term(period, year) VALUES ("H", 2020);
INSERT INTO term(period, year) VALUES ("V", 2020);
INSERT INTO term(period, year) VALUES ("H", 2021);
INSERT INTO term(period, year) VALUES ("V", 2021);
INSERT INTO term(period, year) VALUES ("H", 2022);
INSERT INTO term(period, year) VALUES ("V", 2022);
INSERT INTO term(period, year) VALUES ("H", 2023);
INSERT INTO term(period, year) VALUES ("V", 2023);

/* Users */
INSERT INTO `user`(id, first_name, last_name, password_hash) VALUES (1, "John", "Doe", "dssdwd");
INSERT INTO `user`(id, first_name, last_name, password_hash) VALUES (2, "Jane", "Doe", "ewkwef");

INSERT INTO `user`(id, first_name, last_name, password_hash) VALUES (3, "Ola", "Nordmann", "kflsdl");
INSERT INTO `user`(id, first_name, last_name, password_hash) VALUES (4, "Kari", "Nordmann", "qwklfø");

INSERT INTO `user`(id, first_name, last_name, password_hash) VALUES (5, "Bernt", "Eple", "qiqwik");
INSERT INTO `user`(id, first_name, last_name, password_hash) VALUES (6, "Klara", "Ku", "ewkbcf");

/* Students */
INSERT INTO student(id) VALUES(1)
INSERT INTO student(id) VALUES(2)

/* Teaching assistants */
INSERT INTO teaching_assistant(id) VALUES(3)
INSERT INTO teaching_assistant(id) VALUES(4)

/* Teachers */
INSERT INTO teacher(id) VALUES(5)
INSERT INTO teacher(id) VALUES(6)

/* Subjects */
INSERT INTO subject(code, term_year, term_period) VALUES("IDATT2104", 2021, "V");
INSERT INTO subject(code, term_year, term_period) VALUES("IDATT2105", 2021, "V");
INSERT INTO subject(code, term_year, term_period) VALUES("IDATT2104", 2022, "V");
INSERT INTO subject(code, term_year, term_period) VALUES("IDATT2104", 2022, "V");

/* Assignments */
INSERT INTO assignement(id, `name`, subject_code, subject_term_year, subject_term_period) VALUES(1, "Spis brød", "IDATT2104", 2021, "V");
INSERT INTO assignement(id, `name`, subject_code, subject_term_year, subject_term_period) VALUES(2, "Kast håndkle", "IDATT2105", 2021, "V");
INSERT INTO assignement(id, `name`, subject_code, subject_term_year, subject_term_period) VALUES(3, "Fisk hanskene", "IDATT2105", 2022, "V");

/* Submissions */
INSERT INTO submission(student_id, assignment_id, is_approved, feedback) VALUES (1, 1, 1, "Nydelig jobba");
INSERT INTO submission(student_id, assignment_id, is_approved) VALUES (1, 2, 0);
INSERT INTO submission(student_id, assignment_id, is_approved, feedback) VALUES (2, 2, 1, "Vakkert");
