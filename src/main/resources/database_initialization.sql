CREATE TYPE advisor_role AS ENUM ('Associate', 'Partner', 'Senior');
CREATE TYPE phone_number_type AS ENUM ('Home', 'Work', 'Mobile');
CREATE TYPE application_status AS ENUM ('New', 'Assigned', 'On_hold', 'Approved', 'Canceled', 'Declined');

CREATE TABLE users
(
    id       BIGSERIAL,
    email    TEXT NOT NULL,
    username TEXT NOT NULL,

    CONSTRAINT users_pk PRIMARY KEY (id),
    CONSTRAINT users_email_uq UNIQUE (email),
    CONSTRAINT users_username_uq UNIQUE (username)
);

CREATE TABLE advisors
(
    user_id BIGINT,
    role    advisor_role NOT NULL,

    CONSTRAINT advisors_pk PRIMARY KEY (user_id),
    CONSTRAINT advisors_users_kf FOREIGN KEY (user_id) REFERENCES users
);

CREATE TABLE applicants
(
    user_id    BIGINT,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL,
    ssn        TEXT NOT NULL,

    CONSTRAINT applicants_pk PRIMARY KEY (user_id),
    CONSTRAINT applicants_ssn_uq UNIQUE (ssn),
    CONSTRAINT applicants_users_fk FOREIGN KEY (user_id) REFERENCES users
);

CREATE TABLE addresses
(
    applicant_id BIGINT,
    city         TEXT    NOT NULL,
    street       TEXT    NOT NULL,
    zip          INTEGER NOT NULL,
    apt          TEXT    NOT NULL,

    CONSTRAINT addresses_pk PRIMARY KEY (applicant_id),
    CONSTRAINT addresses_applicants_fk FOREIGN KEY (applicant_id) REFERENCES applicants ON DELETE CASCADE
);

CREATE TABLE phone_numbers
(
    id           BIGSERIAL,
    number       TEXT              NOT NULL,
    phone_type   phone_number_type NOT NULL,

    applicant_id BIGINT            NOT NULL,

    CONSTRAINT phone_numbers_pk PRIMARY KEY (id),
    CONSTRAINT phone_numbers_number_uq UNIQUE (number),
    CONSTRAINT phone_numbers_applicants_fk FOREIGN KEY (applicant_id) REFERENCES applicants ON DELETE CASCADE
);

CREATE TABLE applications
(
    id              BIGSERIAL,
    amount_of_money MONEY              NOT NULL,
    status          application_status NOT NULL DEFAULT 'New',
    created_at      TIMESTAMP          NOT NULL DEFAULT now(),
    assigned_at     TIMESTAMP,

    applicant_id    BIGINT             NOT NULL,
    advisor_id      BIGINT,

    CONSTRAINT applications_pk PRIMARY KEY (id),
    CONSTRAINT applications_applicants_fk FOREIGN KEY (applicant_id) REFERENCES applicants,
    CONSTRAINT applications_advisors_fk FOREIGN KEY (advisor_id) REFERENCES advisors
);

CREATE INDEX applications_applicant_id_idx ON applications (applicant_id);
CREATE INDEX applications_advisor_id_idx ON applications (advisor_id);

CREATE INDEX phone_numbers_applicant_id_idx ON phone_numbers (applicant_id);