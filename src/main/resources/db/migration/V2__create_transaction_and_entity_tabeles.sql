CREATE TABLE transactions (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_from varchar(10) NOT NULL,
  account_to varchar(10)  NOT NULL,
  currency_shortname varchar(4) NOT NULL,
  summary float NOT NULL,
  expense_category varchar(7) NOT NULL,
  limit_exceeded BOOLEAN NOT NULL,
  datetime timestamp NOT NULL
);

CREATE TABLE business_entity (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(45) NOT NULL,
  account varchar(10)  NOT NULL,
  limit_of_goods float NOT NULL,
  limit_of_services float NOT NULL,
  date_of_services_limit timestamp DEFAULT NULL,
  date_of_goods_limit timestamp DEFAULT NULL,
  sum_of_services float NOT NULL,
  sum_of_goods float NOT NULL,
  date_of_sum timestamp NOT NULL
);




