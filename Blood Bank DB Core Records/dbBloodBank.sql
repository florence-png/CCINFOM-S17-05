-- Create Database
CREATE DATABASE IF NOT EXISTS `BloodBankdb`;
USE `BloodBankdb`;

-- CREATE Donor Records
CREATE TABLE IF NOT EXISTS donor (
	donor_id INT AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    contact_info VARCHAR(100),
    age INT CHECK (age >= 18),
    sex CHAR(1) CHECK (sex IN ('M', 'F')),
    date_of_birth DATE,
    blood_type VARCHAR(3) CHECK (blood_type IN ('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-')),
    remarks TEXT
);


-- CREATE Technician Records
CREATE TABLE IF NOT EXISTS technicians (
	technician_id INT PRIMARY KEY,
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    contact_number VARCHAR(15),
    donor_id INT,
    FOREIGN KEY (donor_id) REFERENCES donor(donor_id)
);

-- CREATE Blood Inventory Records
CREATE TABLE IF NOT EXISTS inventory (
	inventory_id INT PRIMARY KEY,
	blood_type VARCHAR(3) CHECK (blood_type IN ('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-')),
    component_type VARCHAR(15),
    recieved_date DATE,
    expiry_date DATE,
    donor_id INT,
    technician_id INT,
    FOREIGN KEY (donor_id) REFERENCES donor(donor_id),
    FOREIGN KEY (technician_id) REFERENCES technicians(technician_id)
);


-- CREATE Blood Bank Branch Records
CREATE TABLE IF NOT EXISTS blood_banks (
	branch_id INT PRIMARY KEY,
    branch_name VARCHAR(50) NOT NULL,
    street_address VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    contact_number VARCHAR(15),
    inventory_id INT,
    FOREIGN KEY (inventory_id) REFERENCES inventory(inventory_id)
);


-- CREATE Hospital Branch Records
CREATE TABLE IF NOT EXISTS hospitals (
	hospital_id INT PRIMARY KEY,
    hospital_name VARCHAR(50) NOT NULL,
    street_address VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    contact_number VARCHAR(15),
    branch_id INT,
    FOREIGN KEY (branch_id) REFERENCES blood_banks(branch_id)
);


-- Unique constraint to prevent duplicate records 
ALTER TABLE Donor
ADD CONSTRAINT unique_donor_combo UNIQUE (first_name, last_name, date_of_birth, blood_type);


-- ###################################################################
-- SAMPLE RECCORDS
-- ###################################################################

-- INSERT Donor Records
INSERT INTO donor (last_name, first_name, contact_info, age, sex, date_of_birth, blood_type, remarks)
VALUES
('Chua'  , 'Aiven'  , '09123456789', '18', 'M', '2007-09-10', 'A+', 'No issues'),
('Almeda', 'Angelo' , '09987654321', '19', 'M', '2006-03-08', 'B-', 'No issues'),
('Teo'   , 'Asia'   , '09214365879', '18', 'M', '2007-02-01', 'O+', 'Broken Wrist'),
('Chua'  , 'Jasper' , '09987645231', '20', 'M', '2005-01-20', 'O-', 'First-time donor'),
('Else'  , 'Someone', '09192837465', '18', 'M', '2007-08-05', 'A-', 'No issues');

-- INSERT Technician Records
INSERT INTO technicians (technician_id, last_name, first_name, contact_number, donor_id)
VALUES
(1, 'Doe', 'John', 091234567, 2),
(2, 'Rivera', 'Matthias', 098765432, 4), 
(3, 'Russell', 'Andrew', 012345678, 3),
(4, 'James', 'Lebron', 098765432, 1);

-- INSERT Blood Inventory Records
INSERT INTO inventory (inventory_id, blood_type, component_type, recieved_date, expiry_date, donor_id, technician_id)
VALUES
(1, 'O-', 'red blood cells', '2026-09-10', '2099-01-01', 4, 2),
(2, 'O+', 'platelets'      , '2027-12-23', '2099-05-05', 3, 3),
(3, 'A+', 'plasma'         , '2018-03-23', '2099-03-03', 1, 4),
(4, 'B-', 'cryoprecipitate', '2029-04-25', '2099-09-09', 2, 1);

-- INSERT Blood Branch Records
INSERT INTO blood_banks (branch_id, branch_name, street_address, city, contact_number, inventory_id)
VALUES
(1, 'Maple Branch', '100 Maple Street', 'Manila', 021234, 1),
(2, 'Hamilton', '321 Burr Street', 'Pasay', 025678, 2),
(3, 'Mysql', '461 El Street', 'Makati', 029012, 3),
(4, 'Sample Branch', '123 Samps Street', 'Sample City', 023456, 4);

-- INSERT Hospital Records
INSERT INTO hospitals (hospital_id, hospital_name, street_address, city, contact_number, branch_id)
VALUES 
(1, 'Pines Standing Hospital', '999 Trese Street', 'Manila', 12345, 1),
(2, 'Mors Central Hospital', '111 Koffine Street', 'Pasay', 67890, 2),
(3, 'Silis Hospital', '455 El Street', 'Makati', 13579, 3),
(4, 'Simpleston General Hospital', '119 Samps Street', 'Sample City', 24680, 4);


