-- Create Database
CREATE DATABASE IF NOT EXISTS `BloodBankdb`;

-- CORE RECORDS
-- CREATE Donor Records
CREATE TABLE IF NOT EXISTS donors (
	donor_id INT AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    donor_email VARCHAR(50) NOT NULL,
	contact_number VARCHAR(15) NOT NULL,
    age INT CHECK (age >= 18) NOT NULL,
    sex ENUM('M', 'F') NOT NULL,
    birthdate DATE NOT NULL,
    blood_type ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-') NOT NULL,
	remarks TEXT,
    status ENUM('Active', 'Inactive') DEFAULT 'Active'
);


-- CREATE Technician Records
CREATE TABLE IF NOT EXISTS technicians (
	technician_id INT PRIMARY KEY,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
	technician_email VARCHAR(50) NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    age INT CHECK (age >= 18) NOT NULL,
    sex ENUM('M', 'F') NOT NULL,
	date_employed DATE NOT NULL,
    status ENUM('Active', 'Inactive') DEFAULT 'Active'
);


-- CREATE Hospital Branch Records
CREATE TABLE IF NOT EXISTS hospitals (
	hospital_id INT PRIMARY KEY,
    hospital_name VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    region VARCHAR(50) NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    status ENUM('Operational', 'Defunct') DEFAULT 'Operational'
);


-- CREATE Blood Bank Branch Records
CREATE TABLE IF NOT EXISTS blood_banks (
	branch_id INT PRIMARY KEY,
    branch_name VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    region VARCHAR(50) NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    status ENUM('Operational', 'Defunct') DEFAULT 'Operational'
);


-- Unique constraint to prevent duplicate records 
ALTER TABLE donors
ADD CONSTRAINT unique_donor_combo UNIQUE (first_name, last_name, birthdate, blood_type);



-- TRANSACTIONAL RECORDS
-- CREATE Blood Inventory Records
CREATE TABLE IF NOT EXISTS inventory (
	inventory_id INT PRIMARY KEY,
	blood_type ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-') NOT NULL,
    component_type ENUM('plasma', 'platelets', 'red blood cells', 'white blood cells') NOT NULL,
    received_date DATE NOT NULL,
    expiry_date DATE NOT NULL,
	remarks TEXT,
    donor_id INT NOT NULL,
    technician_id INT NOT NULL,
    branch_id INT NOT NULL,
    status ENUM('Available', 'Used') DEFAULT 'Available',
    FOREIGN KEY (donor_id) REFERENCES donors(donor_id),
    FOREIGN KEY (technician_id) REFERENCES technicians(technician_id),
    FOREIGN KEY (branch_id) REFERENCES blood_banks(branch_id)
);


-- CREATE Appointment Records
CREATE TABLE IF NOT EXISTS appointments (
	appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    donor_id INT NOT NULL,
    branch_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    technician_id INT NULL,
    status ENUM('Scheduled', 'Completed', 'Cancelled') DEFAULT 'Scheduled',
    FOREIGN KEY (donor_id) REFERENCES donors(donor_id),
    FOREIGN KEY (branch_id) REFERENCES blood_banks(branch_id),
    FOREIGN KEY (technician_id) REFERENCES technicians(technician_id)
);


-- CREATE Hospital Request Records
CREATE TABLE IF NOT EXISTS hospital_request (
	request_id INT AUTO_INCREMENT PRIMARY KEY,
    hospital_id INT NOT NULL,
    branch_id INT NOT NULL,
    blood_type ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-') NOT NULL,
    component_type ENUM('plasma', 'platelets', 'red blood cells', 'white blood cells') NOT NULL,
    units_requested INT NOT NULL,
    request_date DATE NOT NULL,
    status ENUM('Pending', 'Fulfilled', 'Cancelled') DEFAULT 'Pending',
    date_fulfilled DATE NULL,
    FOREIGN KEY (hospital_id) REFERENCES hospitals(hospital_id),
    FOREIGN KEY (branch_id) REFERENCES blood_banks(branch_id)
);




-- ###################################################################
-- SAMPLE RECCORDS (MODIFY TO HAVE AT LEAST 10 RECORDS EACH TABLE)
-- ###################################################################

-- INSERT Donor Records
INSERT INTO donors (last_name, first_name, donor_email, contact_number, age, sex, birthdate, blood_type, remarks)
VALUES
('Chua'  , 'Aiven'  , 'donor-01_@gmail.com', '09123456789', 18, 'M', '2007-09-10', 'A+' , 'No issues'),
('Almeda', 'Angelo' , 'donor-02_@gmail.com', '09987654321', 19, 'M', '2006-03-08', 'B-' , 'No issues'),
('Teo'   , 'Asia'   , 'donor-03_@gmail.com', '09214365879', 18, 'M', '2007-02-01', 'O+' , 'Broken Wrist'),
('Chua'  , 'Jasper' , 'donor-04_@gmail.com', '09987645231', 20, 'M', '2005-01-20', 'O-' , 'First-time donor'),
('Else'  , 'Someone', 'donor-05_@gmail.com', '09192837465', 18, 'M', '2007-08-05', 'A-' , 'No issues'),
('Rizal' , 'Jose'   , 'donor-06_@gmail.com', '12312312312', 18, 'M', '2007-02-13', 'B+' , 'No issues'),
('Name'  , 'Sample' , 'donor-07_@gmail.com', '09809809898', 20, 'F', '2005-07-05', 'AB+', 'No issues'),
('Name'  , 'A'      , 'donor-08_@gmail.com', '01010101010', 28, 'F', '1997-09-27', 'AB-', 'No issues'),
('Name'  , 'Another', 'donor-09_@gmail.com', '19191919199', 30, 'M', '1995-11-01', 'AB-', 'No issues'),
('Person', 'Athird' , 'donor-10_@gmail.com', '12121221212', 19, 'F', '2006-03-30', 'A-' , 'No issues'),
('Guy'   , 'Fourth' , 'donor-11_@gmail.com', '09192837465', 31, 'M', '1994-06-29', 'O-' , 'No issues'),
('Elise' , 'Astrid' , 'donor-12_@gmail.com', '09192837465', 37, 'F', '1988-08-05', 'O-' , 'No issues'),
('Parish', 'Eve'    , 'donor-13_@gmail.com', '09192837465', 27, 'F', '1998-12-10', 'B-' , 'No issues'),
('Eden'  , 'Naomi'  , 'donor-14_@gmail.com', '09192837465', 19, 'F', '2006-01-05', 'A+' , 'No issues'),
('Dela Cruz', 'Juan', 'donor-15_@gmail.com', '12345678901', 24, 'M', '2001-09-11', 'O+' , 'No Issues');

-- INSERT Technician Records
INSERT INTO technicians (technician_id, last_name, first_name, technician_email, contact_number, age, sex, date_employed)
VALUES
(1, 'Doe'    , 'John'    , 'technician01_@gmail.com', '091234567', 27, 'M', '2019-01-18'),
(2, 'Rivera' , 'Matthias', 'technician02_@gmail.com', '098765432', 32, 'M', '2017-08-25'),
(3, 'Russell', 'Andrew'  , 'technician03_@gmail.com', '012345678', 30, 'M', '2020-11-11'),
(4, 'James'  , 'Lebron'  , 'technician04_@gmail.com', '098765432', 37, 'M', '2020-07-15');

-- INSERT Blood Branch Records
INSERT INTO blood_banks (branch_id, branch_name, street_address, city, region, contact_number)
VALUES
(1, 'Maple Branch' , '100 Maple Street', 'Manila'     , 'Region A', '021234'),
(2, 'Hamilton'     , '321 Burr Street' , 'Pasay'      , 'Region B', '025678'),
(3, 'Mysql'        , '461 El Street'   , 'Makati'     , 'Region C', '029012'),
(4, 'Sample Branch', '123 Samps Street', 'Sample City', 'Region D', '023456');

-- INSERT Blood Inventory Records
INSERT INTO inventory (inventory_id, blood_type, component_type, received_date, expiry_date, remarks, donor_id, technician_id, branch_id)
VALUES
(1, 'O-' , 'red blood cells', '2026-09-10', '2099-01-01', 'No Issues', 4, 2, 1),
(2, 'O-' , 'plasma'         , '2026-09-10', '2099-01-01', 'No Issues', 4, 2, 1),
(3, 'O-' , 'platelets'      , '2026-09-10', '2099-01-01', 'No Issues', 4, 2, 1),
(4, 'O-' , 'cryoprecipitate', '2026-09-10', '2099-01-01', 'No Issues', 4, 2, 1),
(5, 'AB-', 'red blood cells', '2024-02-21', '2099-01-01', 'No Issues', 8, 2, 1),
(6, 'AB-', 'plasma'         , '2024-02-21', '2099-01-01', 'No Issues', 8, 2, 1),
(7, 'AB-', 'cryoprecipitate', '2024-02-21', '2099-01-01', 'No Issues', 8, 2, 1),
(8, 'AB-', 'red blood cells', '2022-07-12', '2099-01-01', 'No Issues', 9, 2, 1),
(9, 'AB-', 'plasma'         , '2022-07-12', '2099-01-01', 'No Issues', 9, 2, 1),
(10, 'AB-', 'platelets'      , '2022-08-12', '2099-01-01', 'No Issues', 9, 2, 1),
(11, 'AB-', 'cryoprecipitate', '2022-08-12', '2099-01-01', 'No Issues', 9, 2, 1),
(12, 'A-' , 'red blood cells', '2025-04-04', '2099-01-01', 'No Issues', 10, 2, 1),
(13, 'A-' , 'plasma'         , '2025-04-04', '2099-01-01', 'No Issues', 10, 2, 1),

(14, 'O+', 'plasma'         , '2024-12-23', '2099-05-05', 'No Issues', 3, 3, 2),
(15, 'O+', 'platelets'      , '2024-12-23', '2099-05-05', 'No Issues', 3, 3, 2),
(16, 'O-', 'cryoprecipitate', '2024-05-05', '2099-05-05', 'No Issues', 11, 3, 2),
(17, 'O-', 'platelets'      , '2023-10-14', '2099-05-05', 'No Issues', 12, 3, 2),
(18, 'O-', 'plasma'         , '2023-10-14', '2099-05-05', 'No Issues', 12, 3, 2),
(19, 'B-', 'cryoprecipitate', '2026-06-28', '2099-05-05', 'No Issues', 13, 3, 2),
(20, 'B-', 'plasma'         , '2026-06-28', '2099-05-05', 'No Issues', 13, 3, 2),

(21, 'A+', 'plasma'         , '2018-03-23', '2099-03-03', 'No Issues', 1, 4, 3),
(22, 'A+', 'platelets'      , '2018-03-23', '2099-03-03', 'No Issues', 1, 4, 3),
(23, 'A+', 'plasma'         , '2025-11-12', '2099-03-03', 'No Issues', 14, 4, 3),
(24, 'O+', 'plasma'         , '2025-08-09', '2099-03-03', 'No Issues', 15, 4, 3),

(25, 'A-' , 'red blood cells', '2025-03-30', '2099-09-09', 'No Issues', 5, 1, 4),
(26, 'B+' , 'platelets'      , '2025-12-10', '2099-09-09', 'No Issues', 6, 1, 4),
(27, 'AB+', 'plasma'         , '2025-01-25', '2099-09-09', 'No Issues', 7, 1, 4),
(28, 'B-' , 'cryoprecipitate', '2025-04-25', '2099-09-09', 'No Issues', 2, 1, 4);

-- INSERT Hospital Records
INSERT INTO hospitals (hospital_id, hospital_name, street_address, city, region, contact_number)
VALUES 
(1, 'Pines Standing Hospital'    , '999 Trese Street'  , 'Manila'     , 'Region A', '0012345'),
(2, 'Mors Central Hospital'      , '111 Koffine Street', 'Pasay'      , 'Region B', '0067890'),
(3, 'Silis Hospital'             , '455 El Street'     , 'Makati'     , 'Region C', '0013579'),
(4, 'Simpleston General Hospital', '119 Samps Street'  , 'Sample City', 'Region D', '0024680');


