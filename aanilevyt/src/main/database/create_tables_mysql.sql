CREATE TABLE aanilevyt (
	levyId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	levyNimi VARCHAR(100) NOT NULL,
	artisti VARCHAR(50) NOT NULL,
	julkaisuvuosi INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;