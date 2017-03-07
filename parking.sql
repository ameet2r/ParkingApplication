-- Karanbir Toor and Ameet Toor
-- Create ParkingLot table and generate values.
DROP TABLE ParkingLot;
CREATE TABLE ParkingLot
(
lotName VARCHAR(100),
UNIQUE(lotName),
location VARCHAR(100),
capacity INT,
floors INT,
PRIMARY KEY(lotName)
);
INSERT INTO ParkingLot
VALUES('TestOne', 'Seattle', '100', '2'),
('TTwo', 'Tacoma', '5', '1');

-- Create ParkingSpace and generate values.
DROP TABLE ParkingSpace;
CREATE TABLE ParkingSpace
(
spaceNumber INT,
UNIQUE(spaceNumber),
monthlyRate DECIMAL(10,2) CHECK (monthlyRate >= 0),
lotName VARCHAR(100) REFERENCES ParkingLot(lotName),
taken BOOL,
PRIMARY KEY(spaceNumber)
);
INSERT INTO ParkingSpace
VALUES (1, 12.01, 'TestOne', FALSE),
(3, 1.00, 'TTwo', FALSE),
(2, 0, 'TTwo', FALSE),
(4, 0, 'TTwo', FALSE),
(5, 0, 'TTwo', FALSE),
(6, 0, 'TTwo', FALSE);

-- Create StaffSpace and generate values. 
DROP TABLE StaffSpace;
CREATE TABLE StaffSpace
(
staffNumber INT REFERENCES Staff(staffNumber),
spaceNumber INT REFERENCES ParkingSpace(spaceNumber),
PRIMARY KEY(staffNumber, spaceNumber)
);
INSERT INTO StaffSpace
VALUES (123, 1),
(321, 3);

-- Create Staff and generate values. 
DROP TABLE Staff;
CREATE TABLE Staff
(
staffNumber INT,
UNIQUE(staffNumber),
staffName VARCHAR(100),
telephoneExt VARCHAR(30), -- This is because we may lose leading 0's if it is an int. 
vehicleLicenseNumber VARCHAR(100),
PRIMARY KEY(staffNumber)
);
INSERT INTO Staff
VALUES (123, 'karan', '2221118888', '123icense'),
(321, 'Ameet', '1112223333', '321lincense');

-- Create SpaceBooking and generate values. 
DROP TABLE SpaceBooking;
CREATE TABLE SpaceBooking
(
bookingId INT,
UNIQUE(bookingId),
spaceNumber INT REFERENCES ParkingSpace(spaceNumber),
staffNumber INT REFERENCES Staff(staffNumber),
visitorLicense VARCHAR(30),
dateOfVisit DATE CHECK (dateOfVisit >= CURDATE),
PRIMARY KEY(bookingId)
);
INSERT INTO SpaceBooking
VALUES (3, 1, 123, '123visitorLic', '02-03-18'),
(2, 3, 321, '321visitorLic', '03-03-17');
