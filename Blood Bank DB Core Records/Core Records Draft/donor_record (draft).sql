-- Database: BloodBankdb
-- Table: Donor Records
-- Creator: Aiven Chua

-- Create database
CREATE DATABASE IF NOT EXISTS BloodBankdb;
USE BloodBankdb;

-- Create Donor record table
CREATE TABLE IF NOT EXISTS Donor(
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

-- Unique constraint to prevent duplicate records 
ALTER TABLE Donor
ADD CONSTRAINT unique_donor_combo UNIQUE (first_name, last_name, date_of_birth, blood_type);
    
-- Insert samples
INSERT INTO Donor (last_name, first_name, contact_info, age, sex, date_of_birth, blood_type, remarks)
VALUES
('Chua', 'Aiven', '09123456789', '18', 'M', '2007-9-10', 'A+', 'No issues'),
('Almeda', 'Angelo', '09987654321', '19', 'M', '2006-3-8', 'B-', 'No issues'),
('Teo', 'Asia', '09214365879', '18', 'M', '2007-2-1', 'O+', 'Broken Wrist'),
('Chua', 'Jasper', '09987645231', '20', 'M', '2005-1-20', 'O-', 'First-time donor'),
('Else', 'Someone', '09192837465', '18', 'M', '2007-8-5', 'A-', 'No issues');

-- Sample queries:
-- View all records
SELECT * FROM Donor;

-- View specific donor id
SELECT * FROM Donor WHERE donor_id IS NULL;

-- View donors with O+ blood type
SELECT * FROM Donor WHERE blood_type = 'O+';