-- Add countries.
INSERT INTO countries(country) VALUES 
    ROW('Canada'),
    ROW('United Kingdom'),
    ROW('United States');

-- Add first levels
INSERT INTO first_level_divisions(country_id, division)
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
SELECT country_id, province FROM province_cte CROSS JOIN canada_cte;
    

INSERT INTO first_level_divisions(country_id, division)
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
        ROW('Wales')
    ) t (region)
)
SELECT country_id, region FROM province_cte CROSS JOIN uk_cte;


INSERT INTO first_level_divisions(country_id, division)
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
        ROW('California')
    ) t (region)
)
SELECT country_id, region FROM state_cte CROSS JOIN us_cte;

INSERT INTO customers (
    customer_name,
    address,
    postal_code,
    phone,
    create_date,
    created_by,
    last_update,
    last_updated_by,
    division_id
) VALUES (
    'terry the tester', 
    '123 street st',
    '33333',
    '111-111-1111',
    CURRENT_TIMESTAMP(),
    'admin',
    CURRENT_TIMESTAMP(),
    'admin',
    1
);
