CREATE TABLE aanilevyt (
levyId INT NOT NULL AUTO_INCREMENT,
levyNimi VARCHAR(255) NOT NULL,
artisti VARCHAR(255) NOT NULL,
julkaisuvuosi INT(4) NOT NULL,
PRIMARY KEY (levyId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;