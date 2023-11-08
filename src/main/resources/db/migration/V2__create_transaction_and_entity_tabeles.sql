CREATE TABLE transactions (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_from int NOT NULL,
  account_to int NOT NULL,
  currency_shortname varchar(4) NOT NULL,
  summary float NOT NULL,
  expense_category varchar(7) NOT NULL,
  limit_exceeded BOOLEAN NOT NULL,
  datetime timestamp NOT NULL
);

CREATE TABLE business_entity (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(45) NOT NULL,
  account int NOT NULL,
  limit_of_goods int NOT NULL,
  limit_of_services int NOT NULL,
  date_of_services_limit timestamp DEFAULT NULL,
  date_of_goods_limit timestamp DEFAULT NULL,
  sum_of_services float NOT NULL,
  sum_of_goods float NOT NULL,
  date_of_sum timestamp NOT NULL
);




