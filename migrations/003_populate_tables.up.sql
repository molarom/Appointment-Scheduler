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
WITH uk_cte AS (
    SELECT 
        country_id 
    FROM countries
    WHERE
        country = 'United States'
), province_cte AS (
    SELECT * FROM ( VALUES
        ROW('Alabama'),
        ROW('Alaska'),
        ROW('Arizona'),
        ROW('Arkansas'),
        ROW('California')
    ) t (region)
)
SELECT country_id, region FROM province_cte CROSS JOIN uk_cte;
