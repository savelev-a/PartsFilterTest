CREATE TABLE parts (
    partname    varchar(255),
	partnumber  varchar(255),
	vendor      varchar(255),
	qty         int,
	shipped     date,
	recieve     date
	);

INSERT INTO parts (partname, partnumber, vendor, qty, shipped, recieve)
VALUES ('Eclipse MS5145', 13007, 'Eclipse', 21, '2017-09-01', '2017-09-23');
INSERT INTO parts (partname, partnumber, vendor, qty, shipped, recieve)
VALUES ('APC BE400-RS+', 13067, 'APS', 14, '2017-09-05', '2017-10-01');
INSERT INTO parts (partname, partnumber, vendor, qty, shipped, recieve)
VALUES ('Core i3-3200', 13487, 'Intel', 2, '2016-05-01', '2016-06-23');
INSERT INTO parts (partname, partnumber, vendor, qty, shipped, recieve)
VALUES ('HP LaserJet P1006', 13556, 'Hewlett packard', 55, '2017-01-01', '2017-01-01');
INSERT INTO parts (partname, partnumber, vendor, qty, shipped, recieve)
VALUES ('Zebra LP2824 Plus', 13765, 'Zebra', 43, '2017-05-05', '2017-09-23');
INSERT INTO parts (partname, partnumber, vendor, qty, shipped, recieve)
VALUES ('Windows XP pro', 12009, 'Microsoft', 13, '2017-09-01', '2018-01-01');
INSERT INTO parts (partname, partnumber, vendor, qty, shipped, recieve)
VALUES ('VX 520 00378790', 13555, 'Sberbank', 1, '2017-09-01', '2017-09-23');