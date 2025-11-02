CREATE DATABASE IF NOT EXISTS `BloodBankdb`;
USE `BloodBankdb`;

CREATE TABLE technicians (
	technician_id INT PRIMARY KEY,
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    contact_number VARCHAR(15),
    donor_id INT
);

INSERT INTO technicians VALUES (1, 'Doe', 'John', 091234567, 2);
INSERT INTO technicians VALUES (2, 'Rivera', 'Matthias', 091234567, 4);
INSERT INTO technicians VALUES (3, 'Russell', 'Andrew', 091234567, 3);
INSERT INTO technicians VALUES (4, 'James', 'Lebron', 091234567, 1);

SELECT *
from technicians;
