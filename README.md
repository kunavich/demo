This microservice has no API access restrictions and could be integrated into the existing banking system. A microservice can:

1. Receive information about each expense transaction in tenge (KZT), rubles (RUB) and
other currencies in real time and save it in your own database (DB);
2. Keep the monthly spending limit in U.S. dollars (USD) separate for two expense
categories: goods and services. If not set, accept the limit of 1000 USD;
3. Request data on exchange rates of KZT/USD, RUB/USD currency pairs on a daily
interval (1day/daily) and store them in your own database. When calculating rates, use
close data. If they are not available for the current day (weekend or holiday), then use the
data of the last close (previous_close);
4. Mark transactions that have exceeded the monthly transaction limit (technical flag
limit_exceeded);
5. Allow the client to set a new limit. When a new limit is set, the microservice
automatically sets the current date, not allowing it to be set in the past or future tense. It
is forbidden to update existing limits;
6. At the client's request, return a list of transactions that have exceeded the limit, indicating
the limit that has been exceeded (date of setting, limit amount, currency (USD)).

TODO 
1) use JOIN in transactionalService
2) transactional leading zero or out of int problem
3) problem with Unix time