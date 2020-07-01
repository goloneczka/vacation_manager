CREATE SCHEMA IF NOT EXISTS enterprises;
CREATE TABLE IF NOT EXISTS enterprises.enterprise
(
    id                    SERIAL PRIMARY KEY,
    name                  VARCHAR(127) not null,
    free_days             real not null
);
