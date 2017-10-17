CREATE TABLE authority (
  id INTEGER NOT NULL auto_increment PRIMARY KEY,
  role VARCHAR(255) NOT NULL UNIQUE
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 
 CREATE TABLE webuser (
  id INTEGER NOT NULL auto_increment PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password_encrypted VARCHAR(255) NOT NULL,
  enabled TINYINT NOT NULL,
  firstName VARCHAR(255) DEFAULT NULL,
  lastName VARCHAR(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE webuser_authority (
  id INTEGER NOT NULL auto_increment PRIMARY KEY,
  webuser_id INTEGER NOT NULL,
  authority_id INTEGER NOT NULL,
  FOREIGN KEY (webuser_id) REFERENCES webuser(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (authority_id) REFERENCES authority(id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE workHours (
  entryId INTEGER NOT NULL auto_increment PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  hours DECIMAL(4,2) UNSIGNED NOT NULL,
  workDate DATE NOT NULL,
  FOREIGN KEY (username) REFERENCES webuser(username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;