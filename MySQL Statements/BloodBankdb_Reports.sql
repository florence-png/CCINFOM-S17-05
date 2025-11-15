-- BloodBank Database Reports
USE `BloodBankdb`;

-- EDIT ACCORDING TO FINALIZED RECORDS

-- Report #01: Donation History Report
SELECT   d.donor_id,
	     CONCAT(d.first_name, ' ', d.last_name) AS donor_name,
	     COUNT(i.inventory_id) AS total_donations,
	     MONTH(i.received_date) AS month,
	     YEAR(i.received_date) AS year
FROM     inventory i
			JOIN donors d ON i.donor_id = d.donor_id
WHERE    YEAR(i.received_date) = 2025 
			AND MONTH(i.received_date) = 11
GROUP BY d.donor_id, MONTH(i.received_date), YEAR(i.received_date)
ORDER BY total_donations DESC;


-- Report #02: Technician Performance Report
SELECT   t.technician_id,
	     CONCAT(t.first_name, ' ', t.last_name) AS technician_name,
         COUNT(i.technician_id) AS donations_extracted,
	 	 MONTH(i.received_date) AS month,
		 YEAR(i.received_date) AS year
FROM 	 technicians t
			JOIN inventory i ON t.technician_id = i.technician_id
WHERE    YEAR(i.received_date) = 2026 
			AND MONTH(i.received_date) = 9
GROUP BY t.technician_id, technician_name, month, year
ORDER BY t.technician_id;


-- Report #03: Hospital Request History Report
SELECT   h.hospital_id,
		 h.hospital_name,
		 b.branch_id,
	     b.branch_name,
         COUNT(r.hospital_id),
         MONTH(r.request_date) AS month,
		 YEAR(r.request_date) AS year
FROM     requests r
			JOIN hospitals h ON r.hospital_id = h.hospital_id
            JOIN blood_banks b ON r.branch_id = b.branch_id
WHERE    YEAR(r.request_date) = 2025 
			AND MONTH(r.request_date) = 10
GROUP BY h.hospital_id, h.hospital_name, b.branch_id, b.branch_name, month, year
ORDER BY b.branch_id;


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



-- STATEMENTS FOR DEBUGGING PURPOSES:

-- REPORT 04: DEBUGGING
SELECT   *
FROM   	 blood_banks b 
			JOIN inventory i ON b.branch_id = i.branch_id
			JOIN donors d ON i.donor_id = d.donor_id
			JOIN technicians t ON i.technician_id = t.technician_id
WHERE 	 YEAR(i.received_date) = 2024			 -- YEAR  has to be inputted
  			AND MONTH(i.received_date) = 02      -- MONTH has to be inputted
;
