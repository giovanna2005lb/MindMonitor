CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE managers (
    id BIGINT PRIMARY KEY,
    CONSTRAINT fk_managers_user FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE employees (
    id BIGINT PRIMARY KEY,
    department VARCHAR(255),
    manager_id BIGINT NULL,

    CONSTRAINT fk_employees_user FOREIGN KEY (id) REFERENCES users(id),
    CONSTRAINT fk_employees_manager FOREIGN KEY (manager_id) REFERENCES managers(id)
);

CREATE TABLE daily_reports (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    date DATE NOT NULL,

    desmotivation INT NOT NULL,
    overload INT NOT NULL,
    stress INT NOT NULL,
    comment VARCHAR(255),

    CONSTRAINT fk_dailyreport_employee FOREIGN KEY (employee_id) REFERENCES employees(id),
    CONSTRAINT uq_dailyreport UNIQUE (employee_id, date),

    CONSTRAINT ck_desmotivation CHECK (desmotivation BETWEEN 1 AND 10),
    CONSTRAINT ck_overload CHECK (overload BETWEEN 1 AND 10),
    CONSTRAINT ck_stress CHECK (stress BETWEEN 1 AND 10)
);

CREATE TABLE weekly_summaries (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    weekStart DATE NOT NULL,
    weekEnd DATE NOT NULL,

    avgDesmotivation FLOAT NOT NULL,
    avgOverload FLOAT NOT NULL,
    avgStress FLOAT NOT NULL,

    burnoutRisk BOOLEAN NOT NULL,
    aiFeedback VARCHAR(2000),

    CONSTRAINT fk_weeklysummary_employee FOREIGN KEY (employee_id) REFERENCES employees(id)
);
