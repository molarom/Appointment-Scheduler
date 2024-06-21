-- create helper functions for data generation.
DELIMITER $$

CREATE FUNCTION gen_rand_int (rmin int, rmax int) RETURNS int(10)
DETERMINISTIC
NO SQL
SQL SECURITY DEFINER
BEGIN
    RETURN FLOOR(rmin+RAND()*(rmax - rmin));
END$$

CREATE FUNCTION gen_rand_postal_code () RETURNS text
DETERMINISTIC
NO SQL
SQL SECURITY DEFINER
BEGIN
    RETURN CONCAT(
            gen_rand_int(0,9),
            gen_rand_int(0,9),
            gen_rand_int(0,9),
            gen_rand_int(0,9),
            gen_rand_int(0,9)
        );
END$$

CREATE FUNCTION gen_rand_phone_number () RETURNS text
DETERMINISTIC
NO SQL
SQL SECURITY DEFINER
BEGIN
    RETURN CONCAT_WS('-',
            gen_rand_int(100,999),
            gen_rand_int(100,999),
            gen_rand_int(1000,9999)
        );
END$$


DELIMITER ;


-- add: test customers
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
) WITH RECURSIVE seq_cte AS (
    SELECT 
        1 AS v
    UNION ALL 
        SELECT 
            v + 1 
        FROM
            seq_cte WHERE v < 25
) 
SELECT
    CONCAT('terry the tester', v),
    CONCAT(gen_rand_int(100, 1500), ' ', 'Street St.'),
    gen_rand_postal_code(),
    gen_rand_phone_number(),
    CURRENT_TIMESTAMP(),
    'admin',
    CURRENT_TIMESTAMP(),
    'admin',
    (SELECT division_id FROM first_level_divisions ORDER BY RAND() LIMIT 1)
FROM 
    seq_cte;
