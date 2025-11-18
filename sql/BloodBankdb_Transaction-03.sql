-- Transaction #04: PRocessing Hospital Request
USE `BloodBankdb`;

START TRANSACTION;
SET @appoint_id = 21;        -- Modify to accept user input
Set @remark = 'No Issues';   -- Modify to accept user input

-- Initialize for data retrieval
SET @donor_id     = NULL;
SET @blood_type   = NULL;
SET @appoint_date = NULL;
SET @tech_id      = NULL;
SET @branch_id    = NULL;

-- 1. Verify Appointment Status
SELECT *
FROM   appointments
WHERE  technician_id IS NOT NULL
			AND status = 'Scheduled';

-- 2. Record Donation Details to Inventory
SELECT d.blood_type,
	   a.appointment_date,
       a.donor_id,
       a.technician_id,
       a.branch_id
INTO   @blood_type, 
       @appoint_date, 
       @donor_id, 
       @tech_id, 
       @branch_id
FROM   appointments a
			JOIN donors d ON a.donor_id = d.donor_id
WHERE  a.appointment_id = @appoint_id;


INSERT INTO inventory (blood_type, component_type, received_date, expiry_date, remarks, donor_id, technician_id, branch_id, status)
VALUES 
(@blood_type, 'red blood cells'  , @appoint_date, DATE_ADD(@appoint_date, INTERVAL 42 DAY), @remark, @donor_id, @tech_id, @branch_id, 'Available'),
(@blood_type, 'plasma'           , @appoint_date, DATE_ADD(@appoint_date, INTERVAL 1 YEAR), @remark, @donor_id, @tech_id, @branch_id, 'Available'),
(@blood_type, 'platelets'        , @appoint_date, DATE_ADD(@appoint_date, INTERVAL 7  DAY), @remark, @donor_id, @tech_id, @branch_id, 'Available'),
(@blood_type, 'white blood cells', @appoint_date, DATE_ADD(@appoint_date, INTERVAL 1  DAY), @remark, @donor_id, @tech_id, @branch_id, 'Available');

-- 3. Update Appointment Status
UPDATE appointments 
SET    status = 'Completed'
WHERE  appointment_id >= @appoint_id;


-- 4. Generate Output: 

COMMIT;
