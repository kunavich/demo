CREATE INDEX transactions_ibfk_1 ON business_entity (account);

ALTER TABLE transactions ADD FOREIGN KEY (account_from) REFERENCES business_entity(account);



