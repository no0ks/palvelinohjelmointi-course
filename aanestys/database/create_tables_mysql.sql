CREATE TABLE aanestykset (
    aanestysId INT NOT NULL AUTO_INCREMENT,
    kysymys VARCHAR(255) NOT NULL,
    vastausKylla INT,
    vastausEi INT,
    PRIMARY KEY (aanestysId)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;