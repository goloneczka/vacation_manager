CREATE SCHEMA IF NOT EXISTS enterprises;
CREATE TABLE IF NOT EXISTS enterprises.enterprise
(
    id                    SERIAL PRIMARY KEY,
    enterprise_name       VARCHAR(127) not null,
    free_days             real default 20
);

