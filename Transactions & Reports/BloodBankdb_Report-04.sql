-- Transaction #04: Processing Hospital Request
START TRANSACTION

-- UPDATE

-- COMMIT (push) or ROLLBACK (undo)


-- Report #04: Branch Operations Report
SELECT   b.branch_id,
	     b.branch_name,
         COUNT(DISTINCT i.donor_id) AS active_donors,
         COUNT(DISTINCT i.technician_id) AS active_technicians,
         COUNT(i.inventory_id) AS total_transactions,
         MONTH(i.received_date) AS month,
         YEAR(i.received_date) AS year
FROM     blood_banks b 
			JOIN inventory i ON b.branch_id = i.branch_id
			JOIN donors d ON i.donor_id = d.donor_id
			JOIN technicians t ON i.technician_id = t.technician_id
WHERE 	 YEAR(i.received_date) = 2024			-- YEAR  has to be inputted
  			AND MONTH(i.received_date) = 02     -- MONTH has to be inputted
GROUP BY b.branch_id, b.branch_name, month, year
ORDER BY b.branch_id;





-- REPORT 04: DEBUGGING
SELECT   *
FROM   	 blood_banks b 
			JOIN inventory i ON b.branch_id = i.branch_id
			JOIN donors d ON i.donor_id = d.donor_id
			JOIN technicians t ON i.technician_id = t.technician_id
WHERE 	 YEAR(i.received_date) = 2024			 -- YEAR  has to be inputted
  			AND MONTH(i.received_date) = 02      -- MONTH has to be inputted
;