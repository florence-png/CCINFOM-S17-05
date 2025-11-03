-- ###################################################################
-- SAMPLE RECCORDS
-- ###################################################################

-- INSERT Donor Records
INSERT INTO donors (last_name, first_name, contact_info, age, sex, date_of_birth, blood_type, remarks)
VALUES
('Chua'  , 'Aiven'  , '09123456789', 18, 'M', '2007-09-10', 'A+' , 'No issues'),
('Almeda', 'Angelo' , '09987654321', 19, 'M', '2006-03-08', 'B-' , 'No issues'),
('Teo'   , 'Asia'   , '09214365879', 18, 'M', '2007-02-01', 'O+' , 'Broken Wrist'),
('Chua'  , 'Jasper' , '09987645231', 20, 'M', '2005-01-20', 'O-' , 'First-time donor'),
('Else'  , 'Someone', '09192837465', 18, 'M', '2007-08-05', 'A-' , 'No issues'),
('Rizal' , 'Jose'   , '12312312312', 18, 'M', '2007-02-13', 'B+' , 'No issues'),
('Name'  , 'Sample' , '09809809898', 20, 'F', '2005-07-05', 'AB+', 'No issues'),
('Name'  , 'A'      , '01010101010', 28, 'F', '1997-09-27', 'AB-', 'No issues'),
('Name'  , 'Another', '19191919199', 30, 'M', '1995-11-01', 'AB-', 'No issues'),
('Person', 'Athird' , '12121221212', 19, 'F', '2006-03-30', 'A-' , 'No issues'),
('Guy'   , 'Fourth' , '09192837465', 31, 'M', '1994-06-29', 'O-' , 'No issues'),
('Elise' , 'Astrid' , '09192837465', 37, 'F', '1988-08-05', 'O-' , 'No issues'),
('Parish', 'Eve'    , '09192837465', 27, 'F', '1998-12-10', 'B-' , 'No issues'),
('Eden'  , 'Naomi'  , '09192837465', 19, 'F', '2006-01-05', 'A+' , 'No issues'),
('Dela Cruz', 'Juan', '12345678901', 24, 'M', '2001-09-11', 'O+' , 'No Issues');

-- INSERT Technician Records
INSERT INTO technicians (technician_id, last_name, first_name, contact_number)
VALUES
(1, 'Doe'    , 'John'    , 091234567),
(2, 'Rivera' , 'Matthias', 098765432),
(3, 'Russell', 'Andrew'  , 012345678),
(4, 'James'  , 'Lebron'  , 098765432);

-- INSERT Blood Branch Records
INSERT INTO blood_banks (branch_id, branch_name, street_address, city, contact_number)
VALUES
(1, 'Maple Branch' , '100 Maple Street', 'Manila'     , 021234),
(2, 'Hamilton'     , '321 Burr Street' , 'Pasay'      , 025678),
(3, 'Mysql'        , '461 El Street'   , 'Makati'     , 029012),
(4, 'Sample Branch', '123 Samps Street', 'Sample City', 023456);

-- INSERT Blood Inventory Records
INSERT INTO inventory (inventory_id, blood_type, component_type, received_date, expiry_date, donor_id, technician_id, branch_id)
VALUES
(1, 'O-' , 'red blood cells', '2026-09-10', '2099-01-01', 4, 2, 1),
(1, 'O-' , 'plasma'         , '2026-09-10', '2099-01-01', 4, 2, 1),
(1, 'O-' , 'platelets'      , '2026-09-10', '2099-01-01', 4, 2, 1),
(1, 'O-' , 'cryoprecipitate', '2026-09-10', '2099-01-01', 4, 2, 1),
(1, 'AB-', 'red blood cells', '2024-02-21', '2099-01-01', 8, 2, 1),
(1, 'AB-', 'plasma'         , '2024-02-21', '2099-01-01', 8, 2, 1),
(1, 'AB-', 'cryoprecipitate', '2024-02-21', '2099-01-01', 8, 2, 1),
(1, 'AB-', 'red blood cells', '2022-07-12', '2099-01-01', 9, 2, 1),
(1, 'AB-', 'plasma'         , '2022-07-12', '2099-01-01', 9, 2, 1),
(1, 'AB-', 'platelets'      , '2022-08-12', '2099-01-01', 9, 2, 1),
(1, 'AB-', 'cryoprecipitate', '2022-08-12', '2099-01-01', 9, 2, 1),
(1, 'A-' , 'red blood cells', '2025-04-04', '2099-01-01', 10, 2, 1),
(1, 'A-' , 'plasma'         , '2025-04-04', '2099-01-01', 10, 2, 1),

(2, 'O+', 'plasma'         , '2024-12-23', '2099-05-05', 3, 3, 2),
(2, 'O+', 'platelets'      , '2024-12-23', '2099-05-05', 3, 3, 2),
(2, 'O-', 'cryoprecipitate', '2024-05-05', '2099-05-05', 11, 3, 2),
(2, 'O-', 'platelets'      , '2023-10-14', '2099-05-05', 12, 3, 2),
(2, 'O-', 'plasma'         , '2023-10-14', '2099-05-05', 12, 3, 2),
(2, 'B-', 'cryoprecipitate', '2026-06-28', '2099-05-05', 13, 3, 2),
(2, 'B-', 'plasma'         , '2026-06-28', '2099-05-05', 13, 3, 2),

(3, 'A+', 'plasma'         , '2018-03-23', '2099-03-03', 1, 4, 3),
(3, 'A+', 'platelets'      , '2018-03-23', '2099-03-03', 1, 4, 3),
(3, 'A+', 'plasma'         , '2025-11-12', '2099-03-03', 14, 4, 3),
(3, 'O+', 'plasma'         , '2025-08-09', '2099-03-03', 15, 4, 3),

(4, 'A-' , 'red blood cells', '2025-03-30', '2099-09-09', 5, 1, 4),
(4, 'B+' , 'platelets'      , '2025-12-10', '2099-09-09', 6, 1, 4),
(4, 'AB+', 'plasma'         , '2025-01-25', '2099-09-09', 7, 1, 4),
(4, 'B-' , 'cryoprecipitate', '2025-04-25', '2099-09-09', 2, 1, 4);

-- INSERT Hospital Records
INSERT INTO hospitals (hospital_id, hospital_name, street_address, city, contact_number, branch_id)
VALUES 
(1, 'Pines Standing Hospital', '999 Trese Street', 'Manila', 12345, 1),
(2, 'Mors Central Hospital', '111 Koffine Street', 'Pasay', 67890, 2),
(3, 'Silis Hospital', '455 El Street', 'Makati', 13579, 3),
(4, 'Simpleston General Hospital', '119 Samps Street', 'Sample City', 24680, 4);


