CREATE SCHEMA IF NOT EXISTS leave;
CREATE TABLE IF NOT EXISTS leave.paid_leave
(
    id                    SERIAL PRIMARY KEY,
    start_date            date not null,
    days                  REAL not null,
    employee_id           INTEGER not null REFERENCES workers.worker(id) ON UPDATE CASCADE ON DELETE CASCADE
);