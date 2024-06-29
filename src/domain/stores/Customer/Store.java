package domain.stores.Customer;

import domain.database.SQL;
import domain.database.models.Rows;
import domain.time.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * Store represents a data store for Customer objects.
 *
 * @author Brandon Epperson
 */
public class Store {
    private final SQL db;

    /**
     * Creates a new Store to retrieve Customer documents from the database.
     *
     * @param db the SQL instance to use.
     */
    public Store(SQL db) {
        this.db = db;
    }

    /**
     * add adds a new customer to the database.
     *
     * @param customer the customer to add
     */
    public boolean add(Customer customer) {
        String query = "INSERT INTO " +
                "customers (" +
                "customer_name, " +
                "address, " +
                "postal_code, " +
                "phone, " +
                "created_by, " +
                "last_updated_by, " +
                "create_date, " +
                "last_update, " +
                "division_id " +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            db.PreparedQueryExec(query,
                    customer.getName(),
                    customer.getAddress(),
                    customer.getPostalCode(),
                    customer.getPhone(),
                    customer.getCreatedBy(),
                    customer.getLastUpdatedBy(),
                    customer.getCreateDate().withZone(Time.UTC).toSqlTimestamp(),
                    customer.getLastUpdate().withZone(Time.UTC).toSqlTimestamp(),
                    customer.getDivisionId()
            );
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * update updates a customer in the database.
     *
     * @param customer the customer to update
     */
    public boolean update(Customer customer) {
        String query = "UPDATE " +
                "customers SET " +
                "customer_name = ?, " +
                "address = ?, " +
                "postal_code = ?, " +
                "phone = ?, " +
                "last_updated_by = ?, " +
                "last_update = ?, " +
                "division_id = ? " +
                "WHERE customer_id = ?";

        try {
            db.PreparedQueryExec(query,
                    customer.getName(),
                    customer.getAddress(),
                    customer.getPostalCode(),
                    customer.getPhone(),
                    customer.getLastUpdatedBy(),
                    customer.getLastUpdate().withZone(Time.UTC).toSqlTimestamp(),
                    customer.getDivisionId(),
                    customer.getCustomerId()
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * delete attempts to delete a customer from the database
     *
     * @param id the id of the customer to delete
     * @return true if the query was successful
     */
    public boolean delete(int id) {
        String query = "DELETE FROM customers WHERE customer_id = ?";
        try {
            db.PreparedQueryExec(query, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Queries the database for a user based on their customer_id.
     * If no customer is found it returns an empty Customer object.
     *
     * @param id the customer_id to search for.
     * @return the customer object
     */
    public Customer getById(int id) {
        String query = "SELECT " +
                "* " +
                "FROM customers " +
                "WHERE customer_id = ? " +
                "LIMIT 1";

        Rows rows = db.PreparedQuery(query, id);
        Customer customer = new Customer();

        return rows.get(0).Scan(customer);
    }

    /**
     * Queries the database for a user based on their customer_id.
     * If no customer is found it returns an empty Customer object.
     *
     * @param name the customer_name to search for.
     * @return the customer object
     */
    public Customer getByName(String name) {
        String query = "SELECT " +
                "* " +
                "FROM customers " +
                "WHERE customer_name = ? " +
                "LIMIT 1";

        Rows rows = db.PreparedQuery(query, name);
        Customer customer = new Customer();

        return rows.get(0).Scan(customer);
    }

    /**
     * getAll attempts to fetch all customers contained in the database.
     * Uses a lambda to scan each row and populate the list.
     *
     * @return a list of all customers
     */
    public List<Customer> getAll() {
        String query = "SELECT " +
                "* " +
                "FROM customers " +
                "ORDER BY customer_id";

        Rows rows = db.PreparedQuery(query);
        List<Customer> customers = new ArrayList<>();
        rows.forEach(row -> {
            Customer customer = new Customer();
            customers.add(row.Scan(customer));
        });
        return customers;
    }

    /**
     * count returns the number of rows contained in the customers table in the database.
     */
    public int count() {
        String query = "SELECT " +
                "count(customer_id) " +
                "FROM customers";

        Rows rows = db.PreparedQuery(query);
        int count = 0;
        return rows.get(0).Scan(count);
    }

    /**
     * maxId returns the largest number in the customer Id column
     */
    public int maxId() {
        String query = "SELECT " +
                "max(customer_id) " +
                "FROM customers";

        Rows rows = db.PreparedQuery(query);

        int max = 0;
        return rows.get(0).Scan(max);
    }

    /**
     * getAllCustomerViews attempts to retrieve all
     * CustomerView information from the database.
     *
     * @return the list of returned CustomerView
     */
    public List<CustomerView> getAllCustomerViews() {
        String query = "SELECT " +
                "c.customer_id, " +
                "c.customer_name, " +
                "c.phone, " +
                "c.address, " +
                "c.postal_code, " +
                "c.created_by, " +
                "c.create_date, " +
                "c.last_updated_by, " +
                "c.last_update, " +
                "d.division_id, " +
                "d.division, " +
                "ct.country_id, " +
                "ct.country " +
                "FROM customers c " +
                "JOIN first_level_divisions d USING (division_id) " +
                "JOIN countries ct USING (country_id) ";

        Rows rows = db.PreparedQuery(query);
        List<CustomerView> customerViewList = new ArrayList<>();

        rows.forEach(row -> {
            CustomerView customerView = new CustomerView();
            row.Scan(customerView);
            customerViewList.add(customerView);
        });

        return customerViewList;
    }

    /**
     * getCustomerReports fetches the number of customers per country
     *
     * @return the list of CustomerReport
     */
    public List<CustomerReport> getCustomerReport() {
        String query = """
                SELECT
                    ct.country,
                    count(c.customer_id) AS count
                FROM
                    customers c
                JOIN
                    first_level_divisions f ON f.division_id
                JOIN
                    countries ct on ct.country_id
                GROUP BY ct.country
                """;

        Rows rows = db.PreparedQuery(query);
        List<CustomerReport> customerReports = new ArrayList<>();

        rows.forEach(row -> {
            CustomerReport customerReport = new CustomerReport();
            row.Scan(customerReport);
            customerReports.add(customerReport);
        });
        return customerReports;
    }
}
