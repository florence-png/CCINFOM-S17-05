-- Transaction #01: Scheduling an Appointment
USE `BloodBankdb`;

START TRANSACTION;
SET @donorInput = 1;			-- Modify to accept user input
SET @branchInput = 1;			-- Modify to accept user input
SET @dateInput = CURDATE();		-- Modify to accept user input

-- 1. Check donor eligibility (age >= 18)
SELECT donor_id, 
       first_name, 
       last_name, 
       age
FROM   donors
WHERE  status = 'Active';

-- 2. Check branch availability
SELECT branch_id, 
       branch_name
FROM   blood_banks
WHERE  status = 'Operational';

-- 3. Schedule an appointment
INSERT INTO appointments (donor_id, branch_id, appointment_date)
VALUES (@donorInput, @branchInput, @dateInput);

-- 4. Generate Output: Updated Scheduled Appointment
SELECT a.appointment_id, 
	   CONCAT(d.first_name, ' ', d.last_name) AS donor_name, 
       b.branch_name, 
       a.appointment_date,
       a.status
FROM   appointments a
		JOIN donors d ON a.donor_id = d.donor_id
		JOIN blood_banks b ON a.branch_id = b.branch_id
WHERE  a.donor_id = @donorInput
		AND a.branch_id = @branchInput
        AND a.appointment_date = @dateInput;

COMMIT;