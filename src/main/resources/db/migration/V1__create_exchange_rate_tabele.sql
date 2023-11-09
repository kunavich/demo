CREATE TABLE exchange_rate (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  symbol varchar(45) NOT NULL,
  rate varchar(45) NOT NULL,
  rate_timestamp varchar(45) NOT NULL
);