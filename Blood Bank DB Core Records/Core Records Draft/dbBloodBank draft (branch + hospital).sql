-- Create Database
CREATE DATABASE IF NOT EXISTS `BloodBankdb`;
USE `BloodBankdb`;

-- Table Structure for Blood Bank Branches
CREATE TABLE blood_banks (
	branch_Id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    inventory_Id INT
);


-- Table Structure for Hospital Branches
CREATE TABLE hospitals (
	hospital_Id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL
);



-- Insert Records
INSERT INTO blood_banks VALUES (1, 'Maple Branch', '100 Maple Street', 1);
INSERT INTO blood_banks VALUES (2, 'Hamilton', '321 Burr Street', 2);
INSERT INTO blood_banks VALUES (3, 'Mysql', '461 El Street', 3);
INSERT INTO blood_banks VALUES (4, 'Sample Branch', '123 Samps Street', 4);


INSERT INTO hospitals VALUES (1, 'Pines Standing Hospital', '999 Trese Street');
INSERT INTO hospitals VALUES (2, 'Mors Central Hospital', '111 Koffine Street');
INSERT INTO hospitals VALUES (3, 'Silis Hospital', '455 El Street');
INSERT INTO hospitals VALUES (4, 'Simpleston General Hospital', '119 Samps Street');
