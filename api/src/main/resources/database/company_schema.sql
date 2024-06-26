CREATE SCHEMA IF NOT EXISTS company;
CREATE TABLE IF NOT EXISTS company.enterprise
(
    name                  VARCHAR(127) PRIMARY KEY not null,
    free_days             real default 20,
    confirmed             boolean default false,
    restart_time          date default null
);

CREATE TABLE IF NOT EXISTS company.worker_extra_days
(
    id                    SERIAL PRIMARY KEY,
    seniority             INTEGER not null,
    extra_days            INTEGER,
    annual_extra_days     INTEGER,
    transitive_days       INTEGER default 0
);

CREATE TABLE IF NOT EXISTS company.worker
(
    id                    SERIAL PRIMARY KEY,
    email                 VARCHAR(127) not null,
    enterprise_name       VARCHAR(127) references company.enterprise(name) ON UPDATE CASCADE ON DELETE CASCADE,
    employee_vars_id      INTEGER not null REFERENCES company.worker_extra_days(id) ON UPDATE CASCADE ON DELETE CASCADE,
    password              VARCHAR(127) not null,
    name                  VARCHAR(127) not null,
    occupation            VARCHAR(127) not null,
    confirmed             boolean default false,
    hired                 date default CURRENT_DATE,
    unique (email, enterprise_name)
);

CREATE TABLE IF NOT EXISTS company.role
(
    id                    SERIAL PRIMARY KEY,
    name                  VARCHAR(127) not null
);

CREATE TABLE IF NOT EXISTS company.role_worker
(
    worker_id                INTEGER references company.worker(id) ON UPDATE CASCADE ON DELETE CASCADE,
    role_id                  INTEGER references company.role(id)
);


CREATE TABLE IF NOT EXISTS company.paid_leave
(
    id                    SERIAL PRIMARY KEY,
    start_date            date not null,
    days                  REAL not null,
    employee_id           INTEGER not null REFERENCES company.worker(id) ON UPDATE CASCADE ON DELETE CASCADE,
    end_date              date not null,
    describe              VARCHAR(1024),
    status                VARCHAR(128) default 'NEW' CHECK (status = 'NEW' OR status = 'ACCEPTED' OR status = 'REJECTED')
);


insert into company.role values (1, 'ROLE_CEO');
insert into company.role values (2, 'ROLE_HR');
insert into company.role values (3, 'ROLE_employee');