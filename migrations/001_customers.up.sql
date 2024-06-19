-- Create the customer's document
CREATE TABLE IF NOT EXISTS customers (
    customer_id int(10) NOT NULL AUTO_INCREMENT,
    customer_name varchar(50),
    address varchar(100),
    postal_code varchar(50),
    phone varchar(50),
    create_date datetime,
    created_by varchar(50),
    last_update timestamp,
    last_updated_by varchar(50),
    division_id int(10),
    PRIMARY KEY(customer_id)
);

-- Create the first_level_divisions document
CREATE TABLE IF NOT EXISTS first_level_divisions (
    division_id int(10) NOT NULL AUTO_INCREMENT,
    division varchar(50),
    create_date datetime,
    created_by varchar(50),
    last_update timestamp,
    last_updated_by varchar(50),
    country_id int(10),
    PRIMARY KEY(division_id)
);

-- Add FK constraint
ALTER TABLE customers
    ADD CONSTRAINT fk_customers_has_divisions
    FOREIGN KEY(division_id) REFERENCES first_level_divisions(division_id);

-- Create the countries document.
CREATE TABLE IF NOT EXISTS countries (
    country_id int(10) NOT NULL AUTO_INCREMENT,
    country varchar(50),
    create_date datetime,
    created_by varchar(50),
    last_update timestamp,
    last_updated_by varchar(50),
    PRIMARY KEY(country_id)
);

-- Add FK constraint
ALTER TABLE first_level_divisions
    ADD CONSTRAINT fk_divisions_has_country
    FOREIGN KEY(country_id) REFERENCES countries(country_id);
