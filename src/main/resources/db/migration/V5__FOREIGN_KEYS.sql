ALTER TABLE transactions
ADD FOREIGN KEY (account_from) REFERENCES business_entity(account);

ALTER TABLE business_entity
ADD FOREIGN KEY (account) REFERENCES transactions(account_from);


