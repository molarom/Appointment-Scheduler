-- populate users
INSERT INTO users (
    user_name,
    password,
    create_date,
    created_by,
    last_update,
    last_updated_by
) WITH RECURSIVE seq_cte AS (
    SELECT 
        1 AS v
    UNION ALL 
        SELECT 
            v + 1 
        FROM
            seq_cte WHERE v < 5
) 
SELECT
    CONCAT('test_user', v),
    CONCAT('password', v),
    CURRENT_TIMESTAMP(),
    'admin',
    CURRENT_TIMESTAMP(),
    'admin'
FROM 
    seq_cte;

-- populate contacts
INSERT INTO contacts (
    contact_name,
    email
) WITH RECURSIVE seq_cte AS (
    SELECT 
        1 AS v
    UNION ALL 
        SELECT 
            v + 1 
        FROM
            seq_cte WHERE v < 10
) 
SELECT
    CONCAT('christina the contact', v),
    CONCAT('chrissy', v, '@email.com')
FROM 
    seq_cte;

-- populate appointments
INSERT INTO appointments (
    title,
    description,
    location,
    type,
    start,
    end,
    create_date,
    created_by,
    last_update,
    last_updated_by,
    customer_id,
    user_id,
    contact_id
) WITH RECURSIVE seq_cte AS (
    SELECT 
        1 AS v
    UNION ALL 
        SELECT 
            v + 1 
        FROM
            seq_cte WHERE v < 50
) 
SELECT
    CONCAT('Appointment No. ', v),
    CONCAT('The description for appt ', v),
    CONCAT('Location ', v),
    CONCAT('Important', v),
    DATE_ADD(NOW(), INTERVAL gen_rand_int(0, 1440) MINUTE),
    DATE_ADD(NOW(), INTERVAL gen_rand_int(1440, 5760) MINUTE),
    CURRENT_TIMESTAMP(),
    'admin',
    CURRENT_TIMESTAMP(),
    'admin',
    (SELECT customer_id FROM customers ORDER BY RAND() LIMIT 1),
    (SELECT user_id FROM users ORDER BY RAND() LIMIT 1),
    (SELECT contact_id FROM contacts ORDER BY RAND() LIMIT 1)
FROM 
    seq_cte;
