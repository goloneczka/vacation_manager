CREATE SCHEMA IF NOT EXISTS workers;
CREATE TABLE IF NOT EXISTS workers.worker
(
    id                    SERIAL PRIMARY KEY,
    username              VARCHAR(127) not null,
    enterprise_id         INTEGER not null references enterprises.enterprise(id),
    password              VARCHAR(127) not null,
    name                  VARCHAR(127) not null,
    occupation            VARCHAR(127) not null,
    unique (username, enterprise_id)
);
CREATE TABLE IF NOT EXISTS workers.role
(
    id                    SERIAL PRIMARY KEY,
    name                  VARCHAR(127) not null
);

CREATE TABLE IF NOT EXISTS workers.role_worker
(
    worker_id                INTEGER references workers.worker(id),
    role_id                  INTEGER references workers.role(id)
);

