CREATE TABLE IF NOT EXISTS users (
    user_id int(10) NOT NULL AUTO_INCREMENT,
    user_name varchar(50) UNIQUE,
    password text,
    create_date datetime,
    created_by varchar(50),
    last_update timestamp,
    last_updated_by varchar(50),
    PRIMARY KEY(user_id)
);

CREATE TABLE IF NOT EXISTS contacts (
    contact_id int(10) NOT NULL AUTO_INCREMENT,
    contact_name varchar(50),
    email varchar(50),
    PRIMARY KEY(contact_id)
);

CREATE TABLE IF NOT EXISTS appointments (
    appointment_id int(10) NOT NULL AUTO_INCREMENT,
    title varchar(50),
    description varchar(50),
    location varchar(50),
    type varchar(50),
    start datetime,
    end datetime,
    create_date datetime,
    created_by varchar(50),
    last_update timestamp,
    last_updated_by varchar(50),
    customer_id int(10) REFERENCES customers(customer_id),
    user_id int(10) REFERENCES users(user_id),
    contact_id int(10) REFERENCES contacts(contact_id),
    PRIMARY KEY(appointment_id)
);
