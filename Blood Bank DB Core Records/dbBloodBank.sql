-- Create Database
CREATE DATABASE IF NOT EXISTS `BloodBankdb`;
USE `BloodBankdb`;

-- CREATE Donor Records
CREATE TABLE IF NOT EXISTS donors (
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
    contact_number VARCHAR(15)
);


-- CREATE Blood Bank Branch Records
CREATE TABLE IF NOT EXISTS blood_banks (
	branch_id INT PRIMARY KEY,
    branch_name VARCHAR(50) NOT NULL,
    street_address VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    contact_number VARCHAR(15)
);


-- CREATE Blood Inventory Records
CREATE TABLE IF NOT EXISTS inventory (
	inventory_id INT,
	blood_type VARCHAR(3) CHECK (blood_type IN ('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-')),
    component_type VARCHAR(15),
    received_date DATE,
    expiry_date DATE,
    donor_id INT,
    technician_id INT,
    branch_id INT,
    PRIMARY KEY (inventory_id, component_type, donor_id, technician_id, branch_id),
    FOREIGN KEY (donor_id) REFERENCES donors(donor_id),
    FOREIGN KEY (technician_id) REFERENCES technicians(technician_id),
    FOREIGN KEY (branch_id) REFERENCES blood_banks(branch_id)
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
ALTER TABLE donors
ADD CONSTRAINT unique_donor_combo UNIQUE (first_name, last_name, date_of_birth, blood_type);

-- INSERT STATEMENTS MOVED TO NEW FILE
