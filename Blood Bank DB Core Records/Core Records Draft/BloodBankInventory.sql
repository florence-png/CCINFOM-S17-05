CREATE DATABASE IF NOT EXISTS `BloodBankdb`;
USE `BloodBankdb`;

CREATE TABLE inventory (
  inventory_id VARCHAR(10) NOT NULL,
  blood_type VARCHAR(10) NOT NULL,
  component_type VARCHAR(50) NOT NULL,
  received_date DATE NOT NULL,
  expiry_date DATE,
  donor_id VARCHAR(10),
  technician_id VARCHAR(10)
);

INSERT INTO inventory
VALUES
  ('I001','A+','Whole Blood','2067-09-16','2067-10-20','D001','T001'),
  ('I002','O-','Plasma','2067-09-17','2067-10-21','D002','T002'),
  ('I003','B+','Plasma','2067-09-18','2067-03-22','D003','T001'),
  ('I004','AB-','Platelets','2067-09-19','2067-09-23','D004','T003'),
  ('I005','RH-NULL','Whole Blood','2067-09-20','2067-10-24','D005','T067');

