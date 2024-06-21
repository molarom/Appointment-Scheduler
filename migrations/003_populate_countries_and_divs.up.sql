-- Add countries.
INSERT INTO countries(country, create_date, created_by, last_update, last_updated_by) VALUES 
    ROW('Canada', CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
    ROW('United Kingdom', CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
    ROW('United States', CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin');

-- Add first levels
INSERT INTO first_level_divisions(country_id, 
    division, create_date, created_by, last_update, last_updated_by)
WITH canada_cte AS (
    SELECT 
        country_id 
    FROM countries
    WHERE
        country = 'Canada'
), province_cte AS (
    SELECT * FROM ( VALUES
        ROW('Ontario'),
        ROW('Quebec'),
        ROW('Nova Scotia'),
        ROW('New Brunswick'),
        ROW('Manitoba'),
        ROW('British Columbia'),
        ROW('Prince Edward Island'),
        ROW('Saskatchewan'),
        ROW('Alberta'),
        ROW('Newfoundland')
    ) t (province)
)
SELECT 
    (SELECT country_id FROM canada_cte),
    p.province, 
    CURRENT_TIMESTAMP(), 
    'admin', 
    CURRENT_TIMESTAMP(), 
    'admin'
FROM province_cte p;
    

INSERT INTO first_level_divisions(country_id,
    division, create_date, created_by, last_update, last_updated_by)
WITH uk_cte AS (
    SELECT 
        country_id 
    FROM countries
    WHERE
        country = 'United Kingdom'
), province_cte AS (
    SELECT * FROM ( VALUES
        ROW('England'),
        ROW('Scotland'),
        ROW('Wales'),
        ROW('Ireland')
    ) t (region)
)
SELECT 
    (SELECT country_id FROM uk_cte),
    region, 
    CURRENT_TIMESTAMP(), 
    'admin', 
    CURRENT_TIMESTAMP(), 
    'admin'
FROM province_cte;


INSERT INTO first_level_divisions(country_id,
    division, create_date, created_by, last_update, last_updated_by)
WITH us_cte AS (
    SELECT 
        country_id 
    FROM countries
    WHERE
        country = 'United States'
), state_cte AS (
    SELECT * FROM ( VALUES
        ROW('Alabama'),
        ROW('Alaska'),
        ROW('Arizona'),
        ROW('Arkansas'),
        ROW('California'),
        ROW('Colorado'),
        ROW('Connecticut'),
        ROW('Delaware'),
        ROW('Florida'),
        ROW('Georgia'),
        ROW('Hawaii'),
        ROW('Idaho'),
        ROW('Illinois'),
        ROW('Indiana'),
        ROW('Iowa'),
        ROW('Kansas'),
        ROW('Kentucky'),
        ROW('Louisiana'),
        ROW('Maine'),
        ROW('Maryland'),
        ROW('Massachusetts'),
        ROW('Michigan'),
        ROW('Minnesota'),
        ROW('Mississippi'),
        ROW('Missouri'),
        ROW('Montana'),
        ROW('Nebraska'),
        ROW('Nevada'),
        ROW('New Hampshire'),
        ROW('New Jersey'),
        ROW('New Mexico'),
        ROW('New York'),
        ROW('North Carolina'),
        ROW('North Dakota'),
        ROW('Ohio'),
        ROW('Oklahoma'),
        ROW('Oregon'),
        ROW('Pennsylvania'),
        ROW('Rhode Island'),
        ROW('South Carolina'),
        ROW('South Dakota'),
        ROW('Tennessee'),
        ROW('Texas'),
        ROW('Utah'),
        ROW('Vermont'),
        ROW('Virginia'),
        ROW('Washington'),
        ROW('West Virginia'),
        ROW('Wisconsin'),
        ROW('Wyoming')
    ) t (state)
)
SELECT 
    (SELECT country_id FROM us_cte),
    state, 
    CURRENT_TIMESTAMP(), 
    'admin', 
    CURRENT_TIMESTAMP(), 
    'admin'
FROM state_cte;

