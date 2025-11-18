-- Transaction #04: PRocessing Hospital Request
USE `BloodBankdb`;

START TRANSACTION;
SET @req_id = 4; -- Modify to accept user input
SET @units = 0;  -- Initialize @units

-- 1. Select the Pending Request & Save to Temp Table
CREATE TEMPORARY TABLE selected_request AS
SELECT   request_id, 
		 branch_id,
		 blood_type AS requested_blood,
		 component_requested AS requested_component,
         units_requested
FROM 	 hospital_request
WHERE 	 status = 'Pending'
			AND request_id = @req_id
ORDER BY request_date ASC
LIMIT 1;

-- Extract request details into session variables for easy access
SELECT 
    request_id, 
    units_requested 
INTO 
    @req_id, 
    @units
FROM selected_request;

-- 2. Select Available Inventory using Prepared Statement
SET @sql_inventory = '
    CREATE TEMPORARY TABLE available_inventory AS
    SELECT   i.inventory_id
    FROM     inventory i
				JOIN selected_request sr ON i.branch_id = sr.branch_id
    WHERE    i.branch_id = sr.branch_id 
				AND i.status = ''Available''
				AND i.blood_type = sr.requested_blood
				AND i.component_type = sr.requested_component
    ORDER BY i.expiry_date ASC
    LIMIT    ?';

-- Prepare the statement
PREPARE statement_inventory FROM @sql_inventory;

-- Execute the statement using the @units variable for the LIMIT clause
EXECUTE statement_inventory USING @units;

-- Deallocate the prepared statement
DEALLOCATE PREPARE statement_inventory;


-- Count Found Units
SELECT COUNT(*) INTO @found_units
FROM   available_inventory;


-- 3. Update Inventory Status (Only if Fulfilled)
UPDATE inventory i
JOIN   available_inventory ai ON i.inventory_id = ai.inventory_id
SET    i.status = 'Used'
WHERE  @found_units >= @units;

-- 4. Update Hospital Request Status
UPDATE hospital_request
SET    status = CASE 
					WHEN @found_units >= @units THEN 'Fulfilled' 
                    ELSE 'Cancelled' 
				END,
       date_fulfilled = CASE 
							WHEN @found_units >= @units THEN CURDATE() 
                            ELSE NULL 
						END
WHERE  request_id = @req_id;

-- 6. Generate Outputs
-- Output 01: Updated Processed Inventory Status
SELECT   'Inventory Processed' AS Report,
         i.inventory_id,
	     i.blood_type,
         i.component_type,
         i.expiry_date,
         i.status AS New_Inventory_Status
FROM     inventory i
JOIN     available_inventory ai ON i.inventory_id = ai.inventory_id
ORDER BY i.inventory_id;

-- Output 02: Updated Request Status
SELECT 'Request Status' AS Report,
       r.request_id,
       r.units_requested,
       r.status AS New_Status,
       r.date_fulfilled
FROM   hospital_request r
WHERE  r.request_id = @req_id;


DROP TEMPORARY TABLE IF EXISTS selected_request;
DROP TEMPORARY TABLE IF EXISTS available_inventory;
COMMIT;


