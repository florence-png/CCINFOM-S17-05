-- Transaction #04: PRocessing Hospital Request
USE `BloodBankdb`;

START TRANSACTION;
SET @tech_id = 1;			 -- Modify to accept user input
SET @appoint_id = 21;        -- Modify to accept user input

-- 1. Check Technician Availability
SELECT technician_id, 
       first_name, 
       last_name, 
       age
FROM   technicians
WHERE  status = 'Active';

-- 2. Assign Technician to Appointment
UPDATE appointments a
SET    technician_id   = @tech_id 
WHERE  appointment_id  = @appoint_id
			AND technician_id IS NULL
            AND status = 'Scheduled';

-- 3. Generate Output: Updated Scheduled Appointment
SELECT a.appointment_id, 
	   CONCAT(d.first_name, ' ', d.last_name) AS donor_name, 
       b.branch_name, 
       a.appointment_date, 
       CONCAT(t.first_name, ' ', t.last_name) AS assigned_technician, 
       a.status
FROM   appointments a
			JOIN donors d      ON a.donor_id = d.donor_id
			JOIN blood_banks b ON a.branch_id = b.branch_id
			JOIN technicians t ON a.technician_id = t.technician_id
WHERE  a.appointment_id = @appoint_id;

COMMIT;
