DROP TABLE IF EXISTS li_t_employee_scores;

CREATE TABLE li_t_employee_scores (
  employee_no_from NUMBER(10) NOT NULL,
  employee_no_to NUMBER(10) NOT NULL,
  score NUMBER(6,2) DEFAULT NULL
);

DROP TABLE IF EXISTS li_t_company_types;

CREATE TABLE li_t_company_types (
  company_type VARCHAR(250) NOT NULL,
  score NUMBER(6,2) DEFAULT NULL
);


DROP TABLE IF EXISTS li_t_operate_years;

CREATE TABLE li_t_operate_years (
  year_no_from NUMBER(10) NOT NULL,
  year_no_to NUMBER(10) NOT NULL,
  score NUMBER(6,2) DEFAULT NULL
);


INSERT INTO li_t_employee_scores (employee_no_from, employee_no_to, score) VALUES
  (1, 1, 1),
  (2, 5, 2),
  (6, 10, 3),
  (11, 50, 5),
  (51, 200, 8),
  (201, 99999, 13);
  
  
INSERT INTO li_t_company_types (company_type, score) VALUES
  ('Sole Proprietorship', 1),
  ('Partnership', 3),
  ('Limited Liability Company', 5),
  ('Others', 0);
  
INSERT INTO li_t_operate_years (year_no_from, year_no_to, score) VALUES
  (0, 1, 1),
  (2, 3, 2),
  (4, 6, 3),
  (7, 10, 5),
  (11, 99999, 13);  
  