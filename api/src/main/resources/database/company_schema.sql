CREATE SCHEMA IF NOT EXISTS company;
CREATE TABLE IF NOT EXISTS company.enterprise
(
    id                    SERIAL PRIMARY KEY,
    enterprise_name       VARCHAR(127) not null UNIQUE,
    free_days             real default 20
);

CREATE TABLE IF NOT EXISTS company.paid_leave
(
    id                    SERIAL PRIMARY KEY,
    start_date            date not null,
    days                  REAL not null,
    employee_id           INTEGER not null REFERENCES workers.worker(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS company.worker
(
    id                    SERIAL PRIMARY KEY,
    email                 VARCHAR(127) not null,
    enterprise_id         INTEGER references enterprises.enterprise(id) ON UPDATE CASCADE ON DELETE CASCADE,
    password              VARCHAR(127) not null,
    name                  VARCHAR(127) not null,
    occupation            VARCHAR(127) not null,
    unique (email, enterprise_id)
);
CREATE TABLE IF NOT EXISTS company.role
(
    id                    SERIAL PRIMARY KEY,
    name                  VARCHAR(127) not null
);

CREATE TABLE IF NOT EXISTS company.role_worker
(
    worker_id                INTEGER references workers.worker(id) ON UPDATE CASCADE ON DELETE CASCADE,
    role_id                  INTEGER references workers.role(id)
);
