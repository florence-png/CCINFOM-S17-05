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

-- Sample donor queries
-- View all donor records
SELECT * FROM Donor;

-- View specific donor id
SELECT * FROM Donor WHERE donor_id = 1;

-- View donors with O+ blood type
SELECT * FROM Donor WHERE blood_type = 'O+';



-- Branch and Appointment Section (Scheduling an Appointment)

-- Create Branch table
CREATE TABLE IF NOT EXISTS Branch(
	branch_id INT AUTO_INCREMENT PRIMARY KEY,
    branch_name VARCHAR(100) NOT NULL,
    branch_address VARCHAR(150),
    total_slots INT DEFAULT 10
);

-- Create Appointment table
CREATE TABLE IF NOT EXISTS Appointment(
	appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    donor_id INT NOT NULL,
    branch_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status VARCHAR(20) DEFAULT 'Scheduled',
    
    FOREIGN KEY (donor_id) REFERENCES Donor(donor_id),
    FOREIGN KEY (branch_id) REFERENCES Branch(branch_id)
);

-- Insert sample branch data
INSERT INTO Branch (branch_name, branch_address, total_slots)
VALUES
('Manila Blood Center', 'Taft Ave, Manila', 10),
('Quezon Blood Center', 'Commonwealth Ave, Quezon City', 8);

-- Sample appointment transactions
-- Check donor eligibility (age >= 18)
SELECT donor_id, first_name, last_name, age, remarks
FROM Donor
WHERE age >= 18;

-- Check available branches
SELECT branch_id, branch_name, total_slots
FROM Branch
WHERE total_slots > 0;

-- Schedule an appointment
INSERT INTO Appointment (donor_id, branch_id, appointment_date, appointment_time)
VALUES (1, 1, '2025-11-10', '09:00:00');

-- View all scheduled appointments
SELECT a.appointment_id, d.first_name, d.last_name, b.branch_name, a.appointment_date, a.appointment_time, a.status
FROM Appointment a
JOIN Donor d ON a.donor_id = d.donor_id
JOIN Branch b ON a.branch_id = b.branch_id;

-- Update branch slot after scheduling
UPDATE Branch
SET total_slots = total_slots - 1
WHERE branch_id = 1;



-- =====================================================================
-- Blood Inventory and Donation History Report Section
-- =====================================================================

-- Create Blood Inventory table
CREATE TABLE IF NOT EXISTS BloodInventory(
	inventory_id INT AUTO_INCREMENT PRIMARY KEY,
    donor_id INT NOT NULL,
    branch_id INT NOT NULL,
    blood_type VARCHAR(3),
    received_date DATE,
    expiry_date DATE,
    
    FOREIGN KEY (donor_id) REFERENCES Donor(donor_id),
    FOREIGN KEY (branch_id) REFERENCES Branch(branch_id)
);

-- Insert sample blood inventory data
INSERT INTO BloodInventory (donor_id, branch_id, blood_type, received_date, expiry_date)
VALUES
(1, 1, 'A+', '2025-10-05', '2025-12-05'),
(2, 1, 'B-', '2025-10-15', '2025-12-15'),
(3, 2, 'O+', '2025-11-01', '2026-01-01'),
(1, 1, 'A+', '2025-11-03', '2026-01-03');

-- Sample report query: Donation History Report
-- Show total donations per donor for a given year and month
SELECT 
	d.donor_id,
	CONCAT(d.first_name, ' ', d.last_name) AS donor_name,
	COUNT(bi.inventory_id) AS total_donations,
	MONTH(bi.received_date) AS month,
	YEAR(bi.received_date) AS year
FROM BloodInventory bi
JOIN Donor d ON bi.donor_id = d.donor_id
WHERE YEAR(bi.received_date) = 2025 AND MONTH(bi.received_date) = 11
GROUP BY d.donor_id, MONTH(bi.received_date), YEAR(bi.received_date)
ORDER BY total_donations DESC;
